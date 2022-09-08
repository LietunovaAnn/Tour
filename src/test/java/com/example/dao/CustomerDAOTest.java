package com.example.dao;

import com.example.entities.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CustomerDAOTest {

    @Mock
    private CustomerDAO customerDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getCustomerById_ShouldReturnTrue() throws Exception {
        when(customerDAO.getCustomerById(1)).thenReturn(new Customer(1, "name", "email", 2));
        Customer customer = customerDAO.getCustomerById(1);
        assertThat(customer).isNotNull();
        assertThat(customer.getEmail()).isEqualTo("email");

        verify(customerDAO).getCustomerById(1);

    }


    @Test(expected = Exception.class)
    public void removeCustomer_ShouldThrowException() throws Exception {
        when(customerDAO.removeCustomer(anyInt())).thenThrow(Exception.class);
        customerDAO.removeCustomer(1);
    }

    @Test
    public void testCaptor() throws Exception {
        Customer checkCustomer = new Customer(1, "name", "email", 2);
        when(customerDAO.getCustomer(any(Customer.class)))
                .thenReturn(checkCustomer);
        customerDAO.getCustomer(checkCustomer);
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerDAO).getCustomer(captor.capture());
        Customer customer = captor.getValue();
        assertThat(customer).isEqualTo(checkCustomer);
    }
}