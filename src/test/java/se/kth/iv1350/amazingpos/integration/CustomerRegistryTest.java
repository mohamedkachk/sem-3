package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRegistryTest {
    private CustomerRegistry instance;
    private String customerID;

    @BeforeEach
    void setUp() {
        instance = new CustomerRegistry();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    public void testCustomerControl() {
        customerID = "11111";
        String expResult = customerID;
        String actualResult = instance.customerControl(customerID).getCustomerID();
        assertTrue(actualResult.contains(expResult),"Customer's ID does not exist.");
        assertEquals(expResult, actualResult,"No such customer's ID available.");
    }

    @Test
    public void testCustomerControlIfCustomerID_Is_Wrong() {
        customerID = "1";
        CustomerRegistry expResult = null;
        CustomerRegistry actualResult = instance.customerControl(customerID);
        assertEquals(expResult, actualResult,"Customer's ID is correct.");
    }
}