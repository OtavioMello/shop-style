package br.com.project.shopstyle.mscustomer.controller;

import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import br.com.project.shopstyle.mscustomer.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<URI> postAddress(@RequestBody @Valid AddressDTO addressDTO){
        return ResponseEntity.created(addressService.postAddress(addressDTO)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long id ,@RequestBody @Valid UpdateAddressFORM updateAddressFORM){
        return ResponseEntity.ok(addressService.updateAddressById(id, updateAddressFORM));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
        return ResponseEntity.ok().build();
    }
}
