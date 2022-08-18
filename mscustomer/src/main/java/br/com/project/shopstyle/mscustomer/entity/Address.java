package br.com.project.shopstyle.mscustomer.entity;

import br.com.project.shopstyle.mscustomer.constants.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private State state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String cep;
    private String complement;
    private Long customerId;

}
