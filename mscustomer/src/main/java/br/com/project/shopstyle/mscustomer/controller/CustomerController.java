package br.com.project.shopstyle.mscustomer.controller;

import br.com.project.shopstyle.mscustomer.dto.*;
import br.com.project.shopstyle.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<URI> postCustomer(@RequestBody @Valid CustomerFORM customerFORM){
        return ResponseEntity.created(customerService.postUser(customerFORM)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressDTO> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id,@RequestBody @Valid UpdateCustomerFORM updateCustomerFORM){
        return ResponseEntity.ok(customerService.updateCustomerById(id, updateCustomerFORM));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<CustomerDTO> updateCustomerPassword(@PathVariable Long id, @RequestBody @Valid UpdatePasswordFORM updatePasswordFORM){
        return ResponseEntity.ok(customerService.updateCustomerPassword(id, updatePasswordFORM));
    }
}
