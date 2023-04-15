package org.example.model;

public class ProductItem {
    private String name;
    private long count;


    public ProductItem(String name, long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return name + " (" + count+")"+"\n";
    }
}
