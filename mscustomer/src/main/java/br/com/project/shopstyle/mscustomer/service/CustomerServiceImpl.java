package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.*;
import br.com.project.shopstyle.mscustomer.entity.Address;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.exception.ObjectNotFoundException;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    public static final ObjectNotFoundException CUSTOMER_NOT_FOUND = new ObjectNotFoundException("CUSTOMER NOT FOUND");
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postUser(CustomerFORM customerFORM) {
        Customer customer = customerRepository.save(modelMapper.map(customerFORM, Customer.class));
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(customer.getId());
    }

    @Override
    public CustomerAddressDTO getCustomerById(Long id) {
        return modelMapper.map(customerRepository.findById(id)
                .orElseThrow(() -> CUSTOMER_NOT_FOUND), CustomerAddressDTO.class);
    }

    @Override
    public CustomerDTO updateCustomerById(Long id, UpdateCustomerFORM updateCustomerFORM) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> CUSTOMER_NOT_FOUND);
        String password = customer.getPassword();
        List<Address> addresses = customer.getAddresses();

        customer = modelMapper.map(updateCustomerFORM, Customer.class);
        customer.setId(id);
        customer.setPassword(password);
        customer.setAddresses(addresses);
        return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomerPassword(Long id, UpdatePasswordFORM updatePasswordFORM) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> CUSTOMER_NOT_FOUND);
        customer.setPassword(updatePasswordFORM.getPassword());
        return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
    }

}
