import com.company.*;
import net.bytebuddy.dynamic.ClassFileLocator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankOfMontrealTest {
    @Mock
    ProductPricingService priceAPI;    //mock behaviour of price api

   @Before
    public void setUp(){

       Mockito.when(priceAPI.price(Mockito.anyString(), Mockito.anyString())).thenReturn(250.2);    //first scenario when stock is passed in
       Mockito.when(priceAPI.price(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(52.80);   //handles scenario for when future is passed in

   }

   @Test
    public void testStub(){
       //create mew Product
       Stock stock1 = new Stock("MP25","Ticker1","Ghana Stock Exchange");
       Future future1 = new Future("K201", "Ghana stock exchange","AB2980",2,2021);


       stock1.setPrice(priceAPI.price(stock1.getExchange(),stock1.getTicker()));    //stock now has a value from mocked API
       future1.setPrice(priceAPI.price(future1.getExchange(), future1.getContractCode(), future1.getContractMonth(), future1.getContractYear()));
       Mockito.verify(priceAPI).price(stock1.getExchange(),stock1.getTicker());    //verify that the mock calls its price method for both cases
       Mockito.verify(priceAPI).price(future1.getExchange(), future1.getContractCode(), future1.getContractMonth(), future1.getContractYear());

       Assert.assertEquals(250.2,stock1.getValue(),0.0001);
       Assert.assertEquals(52.80,future1.getValue(),0.0001);


   }

   @Test(expected=ProductAlreadyRegisteredException.class)      //test excepton being thrown when adding a product that already exists

   public void testAddProductException() throws ProductAlreadyRegisteredException {
       BankOFMontrealTradingAPI BOMAPI = new BankOFMontrealTradingAPI();
       //create mew Product
       Stock stock1 = new Stock("MP25","Ticker1","Ghana Stock Exchange");
       stock1.setPrice(priceAPI.price(stock1.getExchange(),stock1.getTicker()));    //stock now has a value from mocked API

       BOMAPI.addNewProduct(stock1);
       BOMAPI.addNewProduct(stock1);
   }

   @Test
   public void testAddProduct(){
       BankOFMontrealTradingAPI BOMAPI = new BankOFMontrealTradingAPI();
       //create mew Product
       Stock stock1 = new Stock("MP25","Ticker1","Ghana Stock Exchange");
       stock1.setPrice(priceAPI.price(stock1.getExchange(),stock1.getTicker()));    //stock now has a value from mocked API
       try {
           BOMAPI.addNewProduct(stock1);
       }catch(ProductAlreadyRegisteredException e){    //catch exception if product is already in the list
           System.err.println(e.getMessage());
       }

       Assert.assertTrue(BOMAPI.getProductsList().contains(stock1));
   }

   @Test
    public void testTrade(){    //test if trade quantity is indeed increased
       BankOFMontrealTradingAPI BOMAPI = new BankOFMontrealTradingAPI();
       //create mew Product
       Stock stock1 = new Stock("MP25","Ticker1","Ghana Stock Exchange");
       stock1.setPrice(priceAPI.price(stock1.getExchange(),stock1.getTicker()));    //stock now has a value from mocked API
       try {
           BOMAPI.addNewProduct(stock1);
       }catch(ProductAlreadyRegisteredException e){    //catch exception if product is already in the list
           System.err.println(e.getMessage());
       }

       BOMAPI.trade(stock1,5);

       Assert.assertEquals(5,BOMAPI.totalTradeQuantityForDay());   //checks total trade quantity for the day for single tradeItem. hence value should equal quantity of that item

       //Add another trade
       Stock stock2 = new Stock("KP25","Ticker2","Ghana Stock Exchange");
       stock2.setPrice(priceAPI.price(stock1.getExchange(),stock1.getTicker()));
       try {
           BOMAPI.addNewProduct(stock2);
       }catch(ProductAlreadyRegisteredException e){    //catch exception if product is already in the list
           System.err.println(e.getMessage());
       }
       BOMAPI.trade(stock2,10);

       Assert.assertEquals(5+10,BOMAPI.totalTradeQuantityForDay());    //total trade quantity for the day should be the sum of the quantities of both products being traded

       Assert.assertEquals(((5*250.2)+(10*250.2)),BOMAPI.totalValueOfDaysTradedProducts(),0.0001) ;    //assert that total value of products is the acc computed value
   }

}
