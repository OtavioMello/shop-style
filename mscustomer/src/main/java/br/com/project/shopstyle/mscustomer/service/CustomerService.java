package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.*;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface CustomerService {
    URI postUser(CustomerFORM customerDTO);

    CustomerAddressDTO getCustomerById(Long id);

    CustomerDTO updateCustomerById(Long id, UpdateCustomerFORM updateCustomerFORM);

    CustomerDTO updateCustomerPassword(Long id, UpdatePasswordFORM updatePasswordFORM);
}
