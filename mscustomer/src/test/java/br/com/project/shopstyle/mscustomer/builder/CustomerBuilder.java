package br.com.project.shopstyle.mscustomer.builder;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import br.com.project.shopstyle.mscustomer.dto.*;
import br.com.project.shopstyle.mscustomer.entity.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CustomerBuilder {

    public static Customer getCustomer(){
        return new Customer(
                1L,
                "536.627.128-22",
                "Cléber",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "cleber@email.com",
                "12345",
                true,
                new ArrayList<>(),
                new HashSet<>()
        );
    }

    public static Customer getUpdatedCustomer(){
        return new Customer(
                1L,
                "536.627.128-22",
                "João",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "joao@email.com",
                "678910",
                true,
                new ArrayList<>(),
                new HashSet<>()
        );
    }

    public static CustomerFORM getCustomerFORM(){
        return new CustomerFORM(
                "536.627.128-22",
                "Cléber",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "cleber@email.com",
                "12345",
                true
        );
    }

    public static UpdateCustomerFORM getUpdateCustomerFORM(){
        return new UpdateCustomerFORM(
                "536.627.128-22",
                "João",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "joao@email.com",
                true
        );
    }

    public static UpdatePasswordFORM getUpdatePasswordFORM(){
        return new UpdatePasswordFORM(
                "678910"
        );
    }

    public static CustomerDTO getCustomerDTO(){
        return new CustomerDTO(
                1L,
                "536.627.128-22",
                "João",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "joao@email.com",
                true
        );
    }

    public static CustomerDTO getUpdatedCustomerDTO(){
        return new CustomerDTO(
                1L,
                "536.627.128-22",
                "João",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "joao@email.com",
                true
        );
    }

    public static CustomerAddressDTO getCustomerAddressDTO(){
        return new CustomerAddressDTO(
                1L,
                "536.627.128-22",
                "João",
                "da Silva",
                Genre.MASCULINO,
                new Date(2003, 5, 9),
                "joao@email.com",
                true,
                new ArrayList<>()
        );
    }
}
