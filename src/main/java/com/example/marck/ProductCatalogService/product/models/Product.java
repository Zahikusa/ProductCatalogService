package com.example.marck.ProductCatalogService.product.models;

public class Product {
    private String name;
    private String type;
    private String description;
    private Integer stock;

    public Product(String name, String type, String description, Integer stock) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.stock = stock;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
