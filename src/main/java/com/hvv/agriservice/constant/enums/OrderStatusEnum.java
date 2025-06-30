package com.hvv.agriservice.constant.enums;

public enum OrderStatusEnum {
    PENDING,                // Đang chờ: Đơn hàng đã được tạo nhưng chưa được xử lý
    PROCESSING,             // Đang xử lý: Đơn hàng đang được nhà bán hàng xử chuẩn bị
    SHIPPED,                // Đã gửi: Đơn hàng đã được gửi đi
    CANCELLED,              // Đã hủy: Đơn hàng bị hủy bởi khách hàng hoặc người bán
    RETURNED,               // Đã trả lại: Khách hàng đã trả lại đơn hàng
    COMPLETED,              // Đã hoàn thành: Đơn hàng đã được xử lý và giao thành công
}
