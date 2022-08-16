package br.com.project.shopstyle.mscustomer.service;

import br.com.project.shopstyle.mscustomer.builder.AddressBuilder;
import br.com.project.shopstyle.mscustomer.builder.CustomerBuilder;
import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import br.com.project.shopstyle.mscustomer.entity.Address;
import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.exception.ObjectNotFoundException;
import br.com.project.shopstyle.mscustomer.repository.AddressRepository;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    CustomerRepository customerRepository;

    @Spy
    ModelMapper modelMapper;

    private Customer customer;
    private Address address;
    private Address updatedAddress;
    private AddressDTO addressDTO;
    private UpdateAddressFORM updateAddressFORM;

    @BeforeEach
    void beforeEach() {
        customer = CustomerBuilder.getCustomer();
        address = AddressBuilder.getAddress();
        updatedAddress = AddressBuilder.getUpdatedAddress();
        addressDTO = AddressBuilder.getAddressDTO();
        updateAddressFORM = AddressBuilder.getUpdateAddressFORM();
    }

    @Test
    void whenPostAddressShouldReturnSuccess() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(addressRepository.save(any())).thenReturn(address);

        URI response = addressService.postAddress(addressDTO);

        assertEquals(URI.class, response.getClass());
        assertEquals("http://localhost/1", response.toString());
    }

    @Test
    void whenUpdateAddressByIdShouldReturnSuccess() {

        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(addressRepository.save(any())).thenReturn(updatedAddress);
        AddressDTO response = addressService.updateAddressById(anyLong(), updateAddressFORM);

        assertNotNull(response);
        assertEquals(AddressDTO.class, response.getClass());
        assertEquals(updateAddressFORM.getCity(), response.getCity());
    }

    @Test
    void whenUpdateAddressByIdShouldThrowObjectNotFoundException(){

        when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("ADDRESS NOT FOUND"));

        try {
            addressService.updateAddressById(anyLong(), updateAddressFORM);
        }catch (Exception e){
            assertNotNull(e);
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("ADDRESS NOT FOUND", e.getMessage());
        }
    }


    @Test
    void whenDeleteAddressByIdShouldReturnsSuccess() {

        when(addressRepository.findById(any())).thenReturn(Optional.of(address));
        doNothing().when(addressRepository).delete(address);
        addressService.deleteAddressById(anyLong());

        verify(addressRepository, times(1)).delete(address);
    }

    @Test
    void whenDeleteAddressByIdShouldThrowObjectNotFoundException(){

        when(addressRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("ADDRESS NOT FOUND"));

        try{
            addressService.deleteAddressById(anyLong());
        }catch (Exception e){
            assertNotNull(e);
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("ADDRESS NOT FOUND", e.getMessage());
        }
    }
}