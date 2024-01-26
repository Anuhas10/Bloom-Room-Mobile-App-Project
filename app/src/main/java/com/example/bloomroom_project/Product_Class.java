package com.example.bloomroom_project;

public class Product_Class {

    private String ProductId;
    private String ProductName;
    private String CategoryId;
    private int Price;
    private int Quantity;


    public Product_Class(){}

    public Product_Class(String productId, String productName, String categoryId, int price, int quantity) {
        ProductId = productId;
        ProductName = productName;
        CategoryId = categoryId;
        Price = price;
        Quantity = quantity;

    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
