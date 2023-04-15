package org.example.model;

import java.util.List;

public class OrderProductItem {
    private long orderId;
    private List<ProductItem> productList;

    public OrderProductItem(long orderId, List<ProductItem> productList) {
        this.orderId = orderId;
        this.productList = productList;
    }

    public long getOrderId() {
        return orderId;
    }

    public List<ProductItem> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "order " + orderId +":"+ " \n "  + productList + "\n"+"-----------------------"+"\n";
    }
}
