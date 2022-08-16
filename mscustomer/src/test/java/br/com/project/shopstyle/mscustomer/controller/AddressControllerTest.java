package br.com.project.shopstyle.mscustomer.controller;

import br.com.project.shopstyle.mscustomer.builder.AddressBuilder;
import br.com.project.shopstyle.mscustomer.dto.AddressDTO;
import br.com.project.shopstyle.mscustomer.dto.UpdateAddressFORM;
import br.com.project.shopstyle.mscustomer.entity.Address;
import br.com.project.shopstyle.mscustomer.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @InjectMocks
    AddressController addressController;

    @Mock
    AddressService addressService;

    private Address address;
    private AddressDTO addressDTO;
    private AddressDTO updatedAddressDTO;

    private UpdateAddressFORM updateAddressFORM;

    @BeforeEach
    void beforeEach() {
        address = AddressBuilder.getAddress();
        updatedAddressDTO = AddressBuilder.getUpdatedAddressDTO();
        addressDTO = AddressBuilder.getAddressDTO();
        updateAddressFORM = AddressBuilder.getUpdateAddressFORM();
    }

    @Test
    void whenPostAddressShouldReturnSuccess() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        when(addressService.postAddress(any())).thenReturn(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(address.getId()));

        ResponseEntity<URI> response = addressController.postAddress(addressDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateAddressByIdShouldReturnSuccess() {

        when(addressService.updateAddressById(anyLong(), any())).thenReturn(updatedAddressDTO);

        ResponseEntity<AddressDTO> response = addressController.updateAddressById(1L, updateAddressFORM);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(updatedAddressDTO.getCity(), response.getBody().getCity());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void whenDeleteAddressByIdShouldReturnSuccess() {

        doNothing().when(addressService).deleteAddressById(anyLong());
        addressController.deleteAddressById(anyLong());

        verify(addressService, times(1)).deleteAddressById(anyLong());
    }
}