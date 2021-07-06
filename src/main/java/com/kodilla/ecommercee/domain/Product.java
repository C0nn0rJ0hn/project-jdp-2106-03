package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    private Long id;
    private String name;
    private String shortDesc;
    private String longDesc;
    private double productPrice;
    private int quantityOnStore;
    private ProductCondition productCondition;
    private double productWeight;
    private boolean stillOnSale;
    private Group group;
    private List<Cart> carts = new ArrayList<>();

    public Product(String name, String shortDesc, String longDesc, double productPrice, int quantityOnStore, ProductCondition productCondition, double productWeight, boolean stillOnSale) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.productPrice = productPrice;
        this.quantityOnStore = quantityOnStore;
        this.productCondition = productCondition;
        this.productWeight = productWeight;
        this.stillOnSale = stillOnSale;
    }

    public Product(Long id, String name, String shortDesc, String longDesc, double productPrice, int quantityOnStore, ProductCondition productCondition, double productWeight, boolean stillOnSale) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.productPrice = productPrice;
        this.quantityOnStore = quantityOnStore;
        this.productCondition = productCondition;
        this.productWeight = productWeight;
        this.stillOnSale = stillOnSale;
    }

    public Product(Long id, String name, String shortDesc, String longDesc, double productPrice, int quantityOnStore, ProductCondition productCondition, double productWeight, boolean stillOnSale, Group group) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.productPrice = productPrice;
        this.quantityOnStore = quantityOnStore;
        this.productCondition = productCondition;
        this.productWeight = productWeight;
        this.stillOnSale = stillOnSale;
        this.group = group;
    }

    public Product() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "SHORT_DESC")
    public String getShortDesc() {
        return shortDesc;
    }

    @Column(name = "LONG_DESC")
    public String getLongDesc() {
        return longDesc;
    }

    @Column(name = "PRODUCT_PRICE")
    public double getProductPrice() {
        return productPrice;
    }

    @Column(name = "QUANTITY_ON_STORE")
    public int getQuantityOnStore() {
        return quantityOnStore;
    }

    @Column(name = "PRODUCT_CONDITION")
    public ProductCondition getProductCondition() {
        return productCondition;
    }

    @Column(name = "PRODUCT_WEIGHT")
    public double getProductWeight() {
        return productWeight;
    }

    @Column(name = "STILL_ON_SALE")
    public boolean isStillOnSale() {
        return stillOnSale;
    }

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup() {
        return group;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    public List<Cart> getCarts() {
        return carts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantityOnStore(int quantityOnStore) {
        this.quantityOnStore = quantityOnStore;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public void setStillOnSale(boolean stillOnSale) {
        this.stillOnSale = stillOnSale;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
