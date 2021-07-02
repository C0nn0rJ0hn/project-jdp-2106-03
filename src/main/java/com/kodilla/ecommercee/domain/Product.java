package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public final class Product {

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
    private Set<Cart> carts;

    public Product() {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    public Long getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public String getShortDesc() {
        return shortDesc;
    }

    @Column
    public String getLongDesc() {
        return longDesc;
    }

    @Column
    public double getProductPrice() {
        return productPrice;
    }

    @Column
    public int getQuantityOnStore() {
        return quantityOnStore;
    }

    @Column
    public ProductCondition getProductCondition() {
        return productCondition;
    }

    @Column
    public double getProductWeight() {
        return productWeight;
    }

    @Column
    public boolean isStillOnSale() {
        return stillOnSale;
    }

    @ManyToOne
    @JoinColumn
    public Group getGroup() {
        return group;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    public Set<Cart> getCarts() {
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

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }
}
