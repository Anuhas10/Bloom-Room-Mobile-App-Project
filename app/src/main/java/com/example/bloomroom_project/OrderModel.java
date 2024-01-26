package com.example.bloomroom_project;

public class OrderModel {

    private String UserId;
    private String productId;
    private String productQty;

    private int id;


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //constructors

    public OrderModel(String UserId, String productId, String productQty){

        this.UserId = UserId;
        this.productId = productId;
        this.productQty = productQty;
    }
}
