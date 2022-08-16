package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.builder.CustomerBuilder;
import br.com.project.shopstyle.mscustomer.dto.*;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.exception.ObjectNotFoundException;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Spy
    ModelMapper modelMapper;

    private Customer customer;
    private Customer updatedCustomer;
    private CustomerFORM customerFORM;
    private UpdateCustomerFORM updateCustomerFORM;
    private UpdatePasswordFORM updatePasswordFORM;

    @BeforeEach
    void beforeEach(){
        customer = CustomerBuilder.getCustomer();
        updatedCustomer = CustomerBuilder.getUpdatedCustomer();
        customerFORM = CustomerBuilder.getCustomerFORM();
        updateCustomerFORM = CustomerBuilder.getUpdateCustomerFORM();
        updatePasswordFORM = CustomerBuilder.getUpdatePasswordFORM();
    }

    @Test
    void whenPostUserShouldShouldReturnSuccess() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        when(customerRepository.save(any())).thenReturn(customer);
        URI response = customerService.postUser(customerFORM);

        verify(customerRepository).save(any());

        assertNotNull(response);
        assertEquals(URI.class, response.getClass());
        assertEquals("http://localhost/1", response.toString());
    }

    @Test
    void whenGetCustomerByIdShouldReturnSuccess() {

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerAddressDTO response = customerService.getCustomerById(anyLong());

        assertNotNull(response);
        assertEquals(CustomerAddressDTO.class, response.getClass());
        assertEquals(response.getId(), customer.getId());

    }

    @Test
    void whenGetCustomerByIdShouldThrowObjectNotFoundException(){

        when(customerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("CUSTOMER NOT FOUND"));

        try {
            customerService.getCustomerById(anyLong());
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("CUSTOMER NOT FOUND", e.getMessage());
        }
    }

    @Test
    void whenUpdateCustomerByIdShouldReturnSuccess() {

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(updatedCustomer);

        CustomerDTO response = customerService.updateCustomerById(customer.getId(), updateCustomerFORM);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(response.getId(), 1L);
        assertEquals(response.getFirstName(), updateCustomerFORM.getFirstName());
        assertEquals(response.getLastName(), updatedCustomer.getLastName());
    }

    @Test
    void whenUpdateCustomerPasswordThenReturnSuccess() {

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(updatedCustomer);
        CustomerDTO response = customerService.updateCustomerPassword(customer.getId(), updatePasswordFORM);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(updatePasswordFORM.getPassword(), updatedCustomer.getPassword());
    }
}