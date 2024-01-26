package com.example.bloomroom_project;

public class Category_Class {

    private String CategoryId;
    private String CategoryName;

    public Category_Class(String categoryId, String categoryName){

        CategoryId = categoryId;
        CategoryName = categoryName;

    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
