package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.CustomerDTO;
import br.com.project.shopstyle.mscustomer.dto.CustomerFORM;
import br.com.project.shopstyle.mscustomer.dto.UpdateCustomerFORM;
import br.com.project.shopstyle.mscustomer.dto.UpdatePasswordFORM;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface CustomerService {
    URI postUser(CustomerFORM customerDTO);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO updateCustomerById(Long id, UpdateCustomerFORM updateCustomerFORM);

    CustomerDTO updateCustomerPassword(Long id, UpdatePasswordFORM updatePasswordFORM);
}
