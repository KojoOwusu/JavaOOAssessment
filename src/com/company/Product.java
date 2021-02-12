package com.company;

public abstract class Product {
    private String ProductID;
    private double currentPrice;

    public Product(String ProductId){
        this.ProductID = ProductId;
    }
    public void setPrice(double value){
    this.currentPrice =value ;
    }

    public double getValue(){
        return currentPrice;
    }
}
