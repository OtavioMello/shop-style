package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import br.com.project.shopstyle.mscustomer.entity.Address;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.exception.ObjectNotFoundException;
import br.com.project.shopstyle.mscustomer.repository.AddressRepository;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    public static final ObjectNotFoundException CUSTOMER_NOT_FOUND = new ObjectNotFoundException("CUSTOMER NOT FOUND");
    public static final ObjectNotFoundException ADDRESS_NOT_FOUND = new ObjectNotFoundException("ADDRESS NOT FOUND");
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postAddress(AddressDTO addressDTO) {
        Customer customer = customerRepository.findById(addressDTO.getCustomerId())
                .orElseThrow(() -> CUSTOMER_NOT_FOUND);
        Address address = addressRepository.save(modelMapper.map(addressDTO, Address.class));
        customer.getAddresses().add(address);
        customerRepository.save(customer);

        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(address.getId());
    }

    @Override
    public AddressDTO updateAddressById(Long id, UpdateAddressFORM updateAddressFORM) {
        Address address = addressRepository.findById(id).orElseThrow(() -> ADDRESS_NOT_FOUND);
        Long customerId = address.getCustomerId();

        address = modelMapper.map(updateAddressFORM, Address.class);
        address.setId(id);
        address.setCustomerId(customerId);

        return modelMapper.map(addressRepository.save(address), AddressDTO.class);
    }

    @Override
    public void deleteAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> ADDRESS_NOT_FOUND);
        addressRepository.delete(address);
    }
}
