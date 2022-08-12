package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.CustomerDTO;
import br.com.project.shopstyle.mscustomer.dto.CustomerFORM;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface CustomerService {
    URI postUser(CustomerFORM customerDTO);

    CustomerDTO getCustomerById(Long id);
}
