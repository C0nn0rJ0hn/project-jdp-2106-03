package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductCondition;

public class ProductDto {
    private Long id;
    private String name;
    private String shortDesc;
    private String longDesc;
    private double productPrice;
    private int quantityOnStore;
    private ProductCondition productCondition;
    private double productWeight;
    private boolean stillOnSale;
    private Product groupId;

    public ProductDto(Long id, String name, String shortDesc, String longDesc, double productPrice, int quantityOnStore,
                      ProductCondition productCondition, double productWeight, boolean stillOnSale, Product groupId) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.productPrice = productPrice;
        this.quantityOnStore = quantityOnStore;
        this.productCondition = productCondition;
        this.productWeight = productWeight;
        this.stillOnSale = stillOnSale;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantityOnStore() {
        return quantityOnStore;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public boolean isStillOnSale() {
        return stillOnSale;
    }

    public Product getGroupId() {
        return groupId;
    }
}
