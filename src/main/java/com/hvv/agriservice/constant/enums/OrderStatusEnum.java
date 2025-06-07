package com.hvv.agriservice.constant.enums;

public enum OrderStatusEnum {
    PENDING,                // Đang chờ: Đơn hàng đã được tạo nhưng chưa được xử lý
    PROCESSING,             // Đang xử lý: Đơn hàng đang được nhà bán hàng xử chuẩn bị
    SHIPPED,                // Đã gửi: Đơn hàng đã được gửi đi
    DELIVERED,              // Đã giao: đơn hàng đã được giao cho khách hàng
    CANCELLED,              // Đã hủy: Đơn hàng bị hủy bởi khách hàng hoặc người bán
    RETURNED,               // Đã trả lại: Khách hàng đã trả lại đơn hàng
    REFUNDED,               // Đã hoàn tiền: Khách hàng đã nhận lại được tiền cho đơn hàng hoàn trả
    ON_HOLD,                // Đang giữ lại: Đơn hàng đang bị tạm giữ
    COMPLETED,              // Đã hoàn thành: Đơn hàng đã được xử lý và giao thành công
    FAILED,                 // Thất bại: Đơn hàng không thành công
}
