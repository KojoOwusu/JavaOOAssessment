package com.company;

import java.time.LocalDate;
import java.time.Month;

public class Future extends Product {
    private String exchange;
    private String contractCode;
    private Integer contractMonth;
    private Integer contractYear;

    public Future(String productId,String exchange, String contractCode, Integer month, Integer contractYear){
         super(productId);
         this.exchange = exchange;
         this.contractCode = contractCode;
         this.contractMonth = month;
         this.contractYear = contractYear;
    }

    public Integer getContractYear() {
        return contractYear;
    }

    public String getContractCode() {
        return contractCode;
    }

    public Integer getContractMonth() {
        return contractMonth;
    }

    public String getExchange() {
        return exchange;
    }

    @Override
    public String toString() {
        return "Future{" +
                "exchange='" + exchange + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", contractMonth=" + contractMonth +
                ", contractYear=" + contractYear +
                '}';
    }
}
