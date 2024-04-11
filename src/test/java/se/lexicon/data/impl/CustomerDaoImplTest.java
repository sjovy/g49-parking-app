package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {

    // Fields
    private CustomerDaoImpl customerList;
    private Customer c1;

    @BeforeEach
    public void setUp() {
        customerList = new CustomerDaoImpl();
        c1 = new Customer("Thomas", "0737417418");
    }

    @Test
    public void testStoreCustomer() {
        Customer actualCustomer = customerList.store(c1);
        assertTrue(customerList.findAll().contains(actualCustomer));
    }

    @Test
    public void testFindById() {
        Customer actualCustomer = customerList.store(c1);
        int ID = c1.getId();
        Optional<Customer> foundCustomer = customerList.find(ID);
        assertTrue(foundCustomer.isPresent());
        assertEquals(actualCustomer, foundCustomer.get());
    }

    @Test
    public void testNonExistentCustomer() {
        Customer actualCustomer = customerList.store(c1);
        Optional<Customer> foundCustomer = customerList.find(9999);
        assertFalse(foundCustomer.isPresent());
    }

    @Test
    public void testRemoveCustomer() {
        Customer actualCustomer = customerList.store(c1);
        int ID = c1.getId();
        assertTrue(customerList.remove(ID));
        assertFalse(customerList.find(ID).isPresent());
    }

    @Test
    public void testRemoveNonExistentCustomer() {
        Customer actualCustomer = customerList.store(c1);
        int ID = 9999;
        assertFalse(customerList.find(ID).isPresent());
        assertFalse(customerList.remove(ID));
    }

    @Test
    public void testFindAllCustomers() {
        //todo: Implement JUnit test
            Customer c2 = new Customer("Lasse", "0736859080");
            Customer c3 = new Customer("Olle", "0731891158");
            customerList.store(c2);
            customerList.store(c3);
            List<Customer> allCustomers = customerList.findAll();

            assertEquals(2, allCustomers.size());
            assertTrue(allCustomers.contains(c2));
            assertTrue(allCustomers.contains(c3));
    }

    @Test
    public void testFindAllCustomersEmptyList() {
        customerList = new CustomerDaoImpl();
        assertTrue(customerList.findAll().isEmpty());
    }

}
