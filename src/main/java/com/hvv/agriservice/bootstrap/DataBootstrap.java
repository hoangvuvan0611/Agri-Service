package com.hvv.agriservice.bootstrap;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.constant.Const;
import com.hvv.agriservice.constant.enums.StatusEnum;
import com.hvv.agriservice.entity.Assets;
import com.hvv.agriservice.entity.Category;
import com.hvv.agriservice.entity.Description;
import com.hvv.agriservice.entity.Product;
import com.hvv.agriservice.repository.AssetRepository;
import com.hvv.agriservice.repository.CategoryRepository;
import com.hvv.agriservice.repository.DescriptionRepository;
import com.hvv.agriservice.repository.ProductRepository;
import com.hvv.agriservice.utils.DataUtils;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class DataBootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;
    private final DescriptionRepository descriptionRepository;
    private final AssetRepository assetRepository;
    private final TransactionalOperator transactionalOperator;

    Logger log = LoggerFactory.getLogger(DataBootstrap.class);

    public DataBootstrap(ProductRepository productRepository, CategoryRepository categoryRepository, SnowflakeIdGenerator snowflakeIdGenerator, DescriptionRepository descriptionRepository, AssetRepository assetRepository, TransactionalOperator transactionalOperator) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.snowflakeIdGenerator = snowflakeIdGenerator;
        this.descriptionRepository = descriptionRepository;
        this.assetRepository = assetRepository;
        this.transactionalOperator = transactionalOperator;
    }

    String productCsvFilePath;
    String categoryCsvFilePath;
    String descriptionCsvFilePath;
    @PostConstruct
    private void init() {
        productCsvFilePath = Const.UrlCommon.PRODUCT_CSV_PATH;
        categoryCsvFilePath = Const.UrlCommon.CATEGORY_CSV_PATH;
        descriptionCsvFilePath = Const.UrlCommon.DESCRIPTION_CSV_PATH;
    }

    @Override
    public void run(String... args) {
        bootstrapData()
                .subscribe(
                        result -> log.info("Bootstrap complete " + result),
                        error -> log.error("Bootstrap failed " + error.getMessage())
                );
    }

    public Mono<String> bootstrapData() {
        return categoryRepository.count()
                .flatMap(count -> {
                    if (count == 0) {
                        return transactionalOperator.transactional(
                                loadCategories()
                                        .flatMap(this::loadProducts)
                                        .flatMap(this::loadDescriptions)
                                        .then(Mono.just("Data bootstrap success"))
                        );
                    } else {
                        return Mono.just("Database already contains data, skipping bootstrap");
                    }
                });
    }

    private <T> Flux<T> readCSVFile(String filePath, Function<CSVRecord, T> mapper, Class<T> clazz) {
        return DataBufferUtils.readInputStream(() -> {
                        try {
                            return new ClassPathResource(filePath).getInputStream();
                        } catch (IOException e) {
                            throw new RuntimeException("Load file error: " + productRepository, e);
                        }
                    },
                    new DefaultDataBufferFactory(),
                    4096
                )
                .reduce(new ByteArrayOutputStream(), (byteArrayOutputStream, dataBuffer) -> {
                    try {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        byteArrayOutputStream.write(bytes);
                        return byteArrayOutputStream;
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .map(byteArrayOutputStream -> new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
                .flatMapMany(byteArrayInputStream -> {
                    try {
                        Reader reader = new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8);
                        CSVParser csvParser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
                        return Flux.fromIterable(csvParser.getRecords());
                    } catch (IOException e) {
                        return Flux.error(new RuntimeException("Error parsing CSV file: ", e));
                    }
                })
                .map(mapper)
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Đọc danh sách category, sinh ID mới, lưu vào database và trả về Map oldId -> newId.
     */
    private Mono<Map<Long, Long>> loadCategories() {
        Map<Long, Long> idMap = new HashMap<>();
        return readCSVFile(categoryCsvFilePath, this::toCategory, Category.class)
                .map(category -> {
                    Long newId = snowflakeIdGenerator.generateId();
                    idMap.put(category.getId(), newId);
                    return Category.builder()
                            .id(newId)
                            .slug(category.getSlug())
                            .name(category.getName())
                            .image(category.getImage())
                            .status(category.getStatus())
                            .isNew(true)
                            .build();
                })
                .collectList()
                .flatMap(categories -> categoryRepository.saveAll(categories)
                        .doOnError(Throwable::printStackTrace)
                        .then(Mono.just(idMap))
                );
    }

    /**
     * Đọc danh sách product, gán ID mới, cập nhật categoryId theo ID mới.
     */
    private Mono<Map<Long, Long>> loadProducts(Map<Long, Long> categoryIdMap) {
        Map<Long, Long> idMap = new HashMap<>();
        return readCSVFile(productCsvFilePath, this::toProduct, Product.class)
                .map(product -> {
                    Long newId = snowflakeIdGenerator.generateId();
                    idMap.put(product.getId(), newId);
                    Long newCategoryId = categoryIdMap.getOrDefault(product.getCategoryId(), null);
                    return Product.builder()
                            .id(newId)
                            .categoryId(newCategoryId)
                            .name(product.getName())
                            .slug(product.getSlug())
                            .unit(product.getUnit())
                            .originalPrice(product.getOriginalPrice())
                            .salePrice(product.getSalePrice())
                            .expiryPeriod(product.getExpiryPeriod())
                            .status(product.getStatus())
                            .isNew(true)
                            .build();
                })
                .collectList()
                .flatMap(products -> productRepository.saveAll(products)
                        .collectList()
                        .flatMap(this::saveProductImages)
                        .doOnError(Throwable::printStackTrace)
                        .then(Mono.just(idMap)));
    }

    /**
     * Luu danh sach anh cho san pham
     */
    private Mono<Void> saveProductImages(List<Product> productList) {
        return Flux.fromIterable(productList)
                .flatMap(product -> {
                    String slug = product.getSlug();
                    Long productId = product.getId();

                    return Mono.fromCallable(() -> {
                        Long assetsId = snowflakeIdGenerator.generateId();
                        String imagePath = slug + ".webp";
                        return Assets.builder()
                                .id(assetsId)
                                .filename(slug)
                                .path(imagePath)
                                .productId(productId)
                                .isNew(true)
                                .build();
                    });
                })
                .subscribeOn(Schedulers.boundedElastic())
                .collectList()
                .flatMapMany(assetRepository::saveAll)
                .doOnError(Throwable::printStackTrace)
                .then();
    }

    /**
     * Đọc danh sách description, cập nhật productId theo ID mới.
     */
    private Mono<Void> loadDescriptions(Map<Long, Long> productIdMap) {
        return readCSVFile(descriptionCsvFilePath, this::toDescription, Description.class)
                .map(description -> {
                    Long newId = snowflakeIdGenerator.generateId();
                    Long newProductId = productIdMap.getOrDefault(description.getProductId(), null);

                    return Description.builder()
                            .id(newId)
                            .productId(newProductId)
                            .certificate(description.getCertificate())
                            .origin(description.getOrigin())
                            .uses(description.getUses())
                            .instructionsForUse(description.getInstructionsForUse())
                            .preservingInstruction(description.getPreservingInstruction())
                            .expiry(description.getExpiry())
                            .isNew(true)
                            .build();
                })
                .collectList()
                .flatMap(entities -> descriptionRepository.saveAll(entities)
                        .doOnError(Throwable::printStackTrace)
                        .then());
    }

    private Product toProduct(CSVRecord record) {
        return Product.builder()
                .id(DataUtils.safeToLong(record.get(0)))
                .categoryId(DataUtils.safeToLong(record.get(1)))
                .slug(record.get(2))
                .unit(record.get(4))
                .name(record.get(3))
                .originalPrice(DataUtils.safeToBigDecimal(record.get(5)))
                .salePrice(DataUtils.safeToBigDecimal(record.get(5)))
                .expiryPeriod(DataUtils.safeToInt(record.get(7)))
                .status(StatusEnum.ACTIVE)
                .build();
    }

    private Category toCategory(CSVRecord record) {
        return Category.builder()
                .id(DataUtils.safeToLong(record.get(0)))
                .slug(record.get(1))
                .name(record.get(2))
                .image(record.get(3))
                .status(StatusEnum.ACTIVE)
                .build();
    }

    private Description toDescription(CSVRecord record) {
        return Description.builder()
                .id(DataUtils.safeToLong(record.get(0)))
                .productId(DataUtils.safeToLong(record.get(1)))
                .certificate(record.get(2))
                .origin(record.get(3))
                .uses(record.get(4))
                .instructionsForUse(record.get(5))
                .preservingInstruction(record.get(6))
                .expiry(record.get(7))
                .build();
    }
}
