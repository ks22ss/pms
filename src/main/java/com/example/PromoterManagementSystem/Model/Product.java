package com.example.PromoterManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(schema = "pms", name = "product")
public class Product {
    @Id
    private String productCode;
    private String productType;
    private String productTypeSpecification;

    private String brandName;
    private Date startDate;
    private Date endDate;

    public Product() {

    }

    public Product(String productCode, String productType, String productTypeSpecification, String brandName, Date startDate, Date endDate) {
        this.productCode = productCode;
        this.productType = productType;
        this.productTypeSpecification = productTypeSpecification;
        this.brandName = brandName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeSpecification() {
        return productTypeSpecification;
    }

    public void setProductTypeSpecification(String productTypeSpecification) {
        this.productTypeSpecification = productTypeSpecification;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
