package org.example.entity;

public class OrderProductEntity {
    private long orderId;
    private long productId;
    private Long count;

    public OrderProductEntity(long orderId, long productId, Long count) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
