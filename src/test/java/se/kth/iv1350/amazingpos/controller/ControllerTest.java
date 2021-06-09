package se.kth.iv1350.amazingpos.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.*;

class ControllerTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private Controller instance;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        instance = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
        instance.startSale();
    }

    @AfterEach
    public void tearDown() {
        printoutContent  = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testStartSale() {
        instance.startSale();
    }

    @Test
    public void testTrueScanItem() {
        String itemId = "apple";
        int quantity = 4;
        String actualResult = instance.scanItem(itemId, quantity);
        String expResult = ""+itemId+" \tquantity: "+quantity+" \ttaxRate: 4  \tprice: 8 ";;
        assertTrue(actualResult.contains(expResult),"Wrong printout.");
    }
    @Test
    public void testFalseScanItem() {
        String itemId = "ap  ple"; // Wrong itemID
        int quantity = 4;
        String actualResult = instance.scanItem(itemId, quantity);
        String expResult = ""+itemId+" \tquantity: "+quantity+" \ttaxRate: 4  \tprice: 8 ";
        assertFalse(actualResult.contains(expResult),"Wrong printout.");
    }
    @Test
    public void testUnavailableItemScanItem() {
        String itemId = "car"; // unavailable item
        int quantity = 4;
        String actualResult = instance.scanItem(itemId, quantity);
        String expResult = "this item is unavailable or invalid!";
        assertEquals(expResult, actualResult," this item is available.");
    }

    @Test
    public void testShowTotalPriceAndVAT() {
        String itemId = "apple";
        int quantity = 4;
        instance.scanItem(itemId, quantity);
        int taxRate = 1;
        int price = 1;
        String expResult = "total VAT: " + (quantity*taxRate)
                +"\t total price: "+((quantity*price)+(quantity*taxRate));
        String actualResult = instance.showTotalPriceAndVAT();
        assertEquals(expResult, actualResult,"Wrong printout of total price and total VAT.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of total price and total VAT.");
    }

    @Test
    public  void testDiscount() {
        String customerID = "11111";
        instance.discount(customerID);
        double discountRate = instance.getSale().getDiscountRate()*100;
        String expResult = "discount rate: "+((int)discountRate)+"%\n";
        String actualResult = printoutContent.toString();
        assertEquals(expResult, actualResult,"Wrong printout of discount rate.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
    }
    @Test
    public  void testDiscountWithWrongCustomerID() {
        String customerID = "00000"; // Wrong customerID
        instance.discount(customerID);
        String expResult ="this customer's ID does not exist";
        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
    }

    @Test
    public void testPay() {
        String itemId = "apple";
        int quantity = 4;
        instance.scanItem(itemId, quantity);
        int taxRate = 1;
        int price = 1;
        instance.showTotalPriceAndVAT();
        String customerID = "11111";
        instance.discount(customerID);
        Sale sale = instance.getSale();
        double discountRate = instance.getSale().getDiscountRate()*100;
        instance.pay(new Amount(100));
        String expResult = "discount rate: "+((int)discountRate)+"%\n"+
                "---------------------RECEIPT---------------------\n"+
                sale.setTimeOfSale().toLocalDate().toString() +
                "\nItems: "+
                "\n"+itemId + " \t" +"quantity: " + quantity + " \t"+
                "taxRate: " + (price*taxRate*quantity) + "  \t"+
                "price: " + ((quantity*price)+(price*taxRate*quantity)) +" \t"+
                "\ntotal price include discount: "+ sale.getTotalPriceAfterDiscount()+" kr"+
                "\ndiscount amount: "+ sale.getDiscountAmount()+" kr"+
                "\n----------------------END----------------------\n";

        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult),
                "Wrong  instance of itemId, customerID or paid amount, check if instances are correct.");
        assertTrue(actualResult.contains(itemId),"Wrong itemId.");
        assertTrue(actualResult.contains(Integer.toString((int)discountRate)),"Wrong customerID.");
        assertTrue(actualResult.contains(Double.toString(sale.getDiscountAmount())),"Wrong customerID.");

    }
}