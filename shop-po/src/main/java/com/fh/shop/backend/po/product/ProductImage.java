package com.fh.shop.backend.po.product;

import java.io.Serializable;

public class ProductImage implements Serializable {
    private static final long serialVersionUID = 3123162575234664710L;

    private Integer id;

    private String imagePath;

    private Product product = new Product();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
