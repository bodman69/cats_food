package org.example.entity;

import java.math.BigDecimal;

public class ProductEntity {
    private long id;
    private String name;
    private long count;
    private BigDecimal price;

    public ProductEntity(long id, String name, long count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
