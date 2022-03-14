package com.example.integrative.models;

public class Product {
    private String productName;
    private String buyingPrice;
    private String sellingPrice;

    public Product(String productName, String buyingPrice, String sellingPrice) {
    this.productName = productName;
    this.buyingPrice = buyingPrice;
    this.sellingPrice = sellingPrice;

    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
