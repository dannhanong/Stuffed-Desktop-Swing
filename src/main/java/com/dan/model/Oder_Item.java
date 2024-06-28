package com.dan.model;

public class Oder_Item {
    private int id;
    private Oder oder;
    private Product product;
    private int quantity;
    private int price;

    public Oder_Item() {
    }

    public Oder_Item(int id, Oder oder, Product product, int quantity, int price) {
        this.id = id;
        this.oder = oder;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Oder getOder() {
        return oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
