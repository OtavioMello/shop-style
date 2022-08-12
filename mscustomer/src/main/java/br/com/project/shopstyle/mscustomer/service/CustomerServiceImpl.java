package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.CustomerDTO;
import br.com.project.shopstyle.mscustomer.dto.CustomerFORM;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postUser(CustomerFORM customerFORM) {
        System.out.println(customerFORM.toString());
        Customer customer = customerRepository.save(modelMapper.map(customerFORM, Customer.class));
        System.out.println(customer.toString());
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(customer.getId());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return modelMapper.map(customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found")), CustomerDTO.class);
    }

}
