package org.example.entity;

import org.example.model.OrderItem;

public class OrderProductEntity {
    private long orderId;
    private long productId;
    private long count;

    public OrderProductEntity(long orderId, long productId, long count) {
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
