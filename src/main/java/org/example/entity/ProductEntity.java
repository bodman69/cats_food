package org.example.entity;

import java.math.BigDecimal;

public class ProductEntity {
    private long id;
    private String name;
    private Long count;
    private BigDecimal price;

    public ProductEntity(long id, String name, Long count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product id= " + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +"\n"+"------------------------------------------------------------"+"\n";
    }
}
