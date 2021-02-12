package com.company;

public interface ProductPricingService {
    Double price(String exchange, String ticker);
    Double price(String exchange, String contractCode, int month, int year);
}
