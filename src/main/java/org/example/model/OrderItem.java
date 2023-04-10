package org.example.model;

public class OrderItem {
    private long productId;
    private long count;

    public OrderItem(long productId, long count) {
        this.productId = productId;
        this.count = count;
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
