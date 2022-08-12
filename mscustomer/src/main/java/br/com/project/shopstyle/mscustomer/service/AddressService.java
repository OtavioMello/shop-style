package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface AddressService {
    URI postAddress(AddressDTO addressDTO);

    AddressDTO updateAddressById(Long id, UpdateAddressFORM updateAddressFORM);

    void deleteAddressById(Long id);
}
