package br.com.project.shopstyle.mscustomer.entity;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Genre sex;
    private Date birthdate;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;
}