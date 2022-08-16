package br.com.project.shopstyle.mscustomer.controller;

import br.com.project.shopstyle.mscustomer.builder.CustomerBuilder;
import br.com.project.shopstyle.mscustomer.dto.*;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    private Customer customer;
    private CustomerFORM customerFORM;
    private UpdateCustomerFORM updateCustomerFORM;
    private UpdatePasswordFORM updatePasswordFORM;
    private CustomerDTO updatedCustomerDTO;
    private CustomerAddressDTO customerAddressDTO;


    @BeforeEach
    void beforeEach() {
        customer = CustomerBuilder.getCustomer();
        customerFORM = CustomerBuilder.getCustomerFORM();
        updateCustomerFORM = CustomerBuilder.getUpdateCustomerFORM();
        updatePasswordFORM = CustomerBuilder.getUpdatePasswordFORM();
        updatedCustomerDTO = CustomerBuilder.getUpdatedCustomerDTO();
        customerAddressDTO = CustomerBuilder.getCustomerAddressDTO();
    }

    @Test
    void whenPostCustomerShouldReturnSuccess() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        when(customerService.postUser(any())).thenReturn(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(customer.getId()));

        ResponseEntity<URI> response = customerController.postCustomer(customerFORM);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenGetCustomerByIdShouldReturnSuccess() {

        when(customerService.getCustomerById(anyLong())).thenReturn(customerAddressDTO);

        ResponseEntity<CustomerAddressDTO> response = customerController.getCustomerById(anyLong());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(customerAddressDTO.getFirstName(), response.getBody().getFirstName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenUpdateCustomerByIdShouldReturnSuccess() {

        when(customerService.updateCustomerById(anyLong(), any())).thenReturn(updatedCustomerDTO);

        ResponseEntity<CustomerDTO> response = customerController.updateCustomerById(1L, updateCustomerFORM);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(updatedCustomerDTO.getFirstName(), response.getBody().getFirstName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenUpdateCustomerPasswordShouldReturnSuccess() {

        when(customerService.updateCustomerPassword(anyLong(), any())).thenReturn(updatedCustomerDTO);

        ResponseEntity<CustomerDTO> response = customerController.updateCustomerPassword(1L, updatePasswordFORM);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}