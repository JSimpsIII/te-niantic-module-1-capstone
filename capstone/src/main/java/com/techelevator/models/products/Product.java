package com.techelevator.models.products;

public class Product {

    //instance variables
    private String rowId;
    private String name;
    private double price;
    private String productType;
    private int quantity;

    //constructors
    public Product() {

    }

    public Product(String rowId, String name, double price, String productType) {
        this.rowId = rowId;
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.quantity = 5;
    }

    //getters and setters
    public String getRowId() {
        return rowId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    //create boolean for quantity
    // should never be negative
    public boolean setQuantity(int quantity) {
        if(quantity < 0) {
            return false;
        }

        this.quantity = quantity;
        return true;
    }

}
