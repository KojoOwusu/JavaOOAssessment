package com.company;

public interface MontrealTradedProducts {
    void addNewProduct(Product product) throws ProductAlreadyRegisteredException;//

    void trade(Product product, int quantity);    //books a quantity against the product traded. if the product has not been registered on quantity is recorded

    int totalTradeQuantityForDay();    //calculates total quantity of all the registered traded products

    double totalValueOfDaysTradedProducts();  //calculates total value of al lregistered traded products with formula value x quantity
}
