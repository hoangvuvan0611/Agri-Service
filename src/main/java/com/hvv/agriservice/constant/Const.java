package com.hvv.agriservice.constant;

public class Const {
    public static final String ALL = "/all";
    public static final String UPDATE = "/update";
    public static final String CREATE = "/create";
    public static final String ID = "/{id}";

    public interface UrlCommon {
        String PRODUCT_CSV_PATH = "metadata/Product.csv";
        String CATEGORY_CSV_PATH = "metadata/Category.csv";
        String DESCRIPTION_CSV_PATH = "metadata/Description.csv";
    }

    public interface OrderPath {
        String ORDER_PATH = "/order";
    }

    public interface ProductPath {
        String PRODUCT_PATH = "/product";
        String GET_RECOMMENDATION_IDS_BY_ID = "/recommend_ids/{id}";
        String GET_RECOMMENDATION_PRODUCTS_BY_ID = "/recommend_products/{id}";
        String GET_PRODUCTS_SHOW_INIT = "/showInit";
        String GET_PRODUCT_BY_SLUG = "/productBySlug={slug}";
        String GET_TOTAL = "/total";
    }

    public interface Common {
        String GET_MANAGEMENT = "/showManagement";
    }

    public interface AssetPath {
        String ASSET_PATH = "/asset";
    }

    public interface CartPath {
        String CART_PATH = "/cart";
    }

    public interface AffiliatePath {
        String AFFILIATE_PATH = "/affiliate";
    }

    public interface AttributePath {
        String ATTRIBUTE_PATH = "/attribute";
    }

    public interface CategoryPath {
        String CATEGORY_PATH = "/category";
    }

    public interface CartItemPath {
        String CART_ITEM_PATH = "/cartItem";
    }

    public interface CityPath {
        String CITY_PATH = "/city";
    }

    public interface CouponPath {
        String COUPON_PATH = "/coupon";
    }

    public interface CustomerPath {
        String CUSTOMER_PATH = "/customer";
    }

    public interface DeliveryInfoPath {
        String DELIVERY_INFO_PATH = "/deliveryInfo";
    }

    public interface DistrictPath {
        String DISTRICT_PATH = "/district";
    }

    public interface OrderItemPath {
        String ORDER_ITEM_PATH = "/orderItem";
    }

    public interface ReviewPath {
        String REVIEW_PATH = "/review";
    }

    public interface RolePath {
        String ROLE_PATH = "/role";
    }

    public interface UserPath {
        String USER_PATH = "/user";
    }

    public interface WardPath {
        String WARD_PATH = "/ward";
    }

    public interface FilePath {
        String FILE_PATH = "/file";
    }
}
