package com.techelevator.models.products;

public class Product {

    private String rowId;
    private String name;
    private double price;
    private String productType;

    public Product() {

    }

    public Product(String rowId, String name, double price, String productType) {
        this.rowId = rowId;
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
