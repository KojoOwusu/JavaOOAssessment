package com.company;

// exception class for case when product has already been added to the trade stock
//should tell user that prooduct has already been registered.

public class ProductAlreadyRegisteredException extends Exception{
    public ProductAlreadyRegisteredException(String message){
        super(message);
    }
}
