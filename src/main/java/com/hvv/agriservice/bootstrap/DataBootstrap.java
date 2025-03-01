package com.hvv.agriservice.bootstrap;

import com.hvv.agriservice.constant.consts.Common;
import com.hvv.agriservice.entity.Product;
import com.hvv.agriservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataBootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;

    Logger log = LoggerFactory.getLogger(DataBootstrap.class);

    public DataBootstrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    String productCsvFilePath;
    String categoryCsvFilePath;
    String descriptionCsvFilePath;
    @PostConstruct
    private void init() {
        productCsvFilePath = Common.UrlCommon.PRODUCT_CSV_PATH;
        categoryCsvFilePath = Common.UrlCommon.CATEGORY_CSV_PATH;
        descriptionCsvFilePath = Common.UrlCommon.DESCRIPTION_CSV_PATH;
    }

    @Override
    public void run(String... args) throws Exception {
//        bootstrapData()
//                .subscribe(
//                        result -> log.info("Bootstrap complete " + result),
//                        error -> log.error("Bootstrap failed " + error.getMessage())
//                );
    }

    private Mono<String> bootstrapData() {
        return productRepository.count().flatMap(count -> {
            if (count == 0) {
                try {
                    List<Product> productList = readDataProductFile();
                    return productRepository.saveAll(productList)
                            .then(Mono.just("Set update success"));
                } catch (Exception e) {
                    log.error("BD000-1: Loi khi thuc hien doc data");
                    return Mono.error(new RuntimeException("BD000-1: Loi khi doc file csv"));
                }
            } else {
                return Mono.just("Database already contains data, skipping bootstrap");
            }
        });
    }

    private List<Product> readDataProductFile() {
        List<Product> productList = new ArrayList<>();
        try (InputStream inputStream = new ClassPathResource(productCsvFilePath).getInputStream();
             Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        ) {
            CSVParser recordIterator = CSVFormat.RFC4180.parse(reader);

            recordIterator.stream().forEach(record -> {
                String name = record.get("name");
                System.out.println(name);
            });


        } catch (IOException e) {
            log.error("RDPF000-1: Loi khi doc file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return productList;
    }
}
