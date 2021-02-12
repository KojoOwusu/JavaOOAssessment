package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankOFMontrealTradingAPI implements MontrealTradedProducts{      //Bank of Montreal trading service that implements the interface MontrealTradedProducts
    private ArrayList<Product> productsList;     //list of products being traded by the company
    private Map<Integer, Integer> quantityPerProduct;     //mapping of product to its corresponding quantity

    public BankOFMontrealTradingAPI(){
        productsList = new ArrayList<>();
        quantityPerProduct = new HashMap<>();
    }
    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {

        //First perform check to see if product is already in the system and throw exception if it does
        // if not, it should add product to the list of products being traded
        if(isProductRegistered(product)){
            throw new ProductAlreadyRegisteredException("sorry there is already a product like this in the system");
        }
        productsList.add(product);
    }

    @Override
    public void trade(Product product, int quantity) {
            if(isProductRegistered(product)){
                quantityPerProduct.put(product.hashCode(), quantity);
            }else{
                System.out.println("Quantity not added. This is nota product being traded right now");
            }
    }

    @Override
    public int totalTradeQuantityForDay() {
        if(quantityPerProduct.isEmpty())
            return 0;
        else{
          return quantityPerProduct.values().stream().reduce(0, (subtotal, element) -> subtotal + element);
           }
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        double sum =0.0;
        if(quantityPerProduct.isEmpty())
            return 0;
        else{
            for(var p: productsList){
                if(quantityPerProduct.containsKey(p.hashCode()))
                sum += p.getValue()*quantityPerProduct.get(p.hashCode());
            }
        }
        return sum;
    }


    private boolean isProductRegistered(Product p){    //check if product has been registered
        return productsList.contains(p);
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }
}
