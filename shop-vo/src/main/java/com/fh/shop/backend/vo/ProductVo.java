package com.fh.shop.backend.vo;

import java.io.Serializable;

public class ProductVo implements Serializable {
    private static final long serialVersionUID = -2787534372005034182L;

    private Integer id;

    private String productName;

    private String productImagePath;

    private Float productPrice;

    private String brandName;

    private String updateTimeStr;

    private String insertTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getInsertTimeStr() {
        return insertTimeStr;
    }

    public void setInsertTimeStr(String insertTimeStr) {
        this.insertTimeStr = insertTimeStr;
    }
}
