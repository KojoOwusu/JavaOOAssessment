package com.company;

public class Stock extends Product{

    private String ticker;    //the stock ticker that shows all the stocks and their information
   private String exchange;     //the exchange the stock is part of example Ghanaian Stock Exchange

   public Stock(String productId, String ticker, String exchange){
            super(productId);
            this.ticker = ticker;
            this.exchange=exchange;
   }

   public String getTicker(){
       return ticker;
   }
   public String getExchange(){
       return exchange;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker=" + ticker +
                ", exchange='" + exchange + '\'' +
                '}';
    }
}
