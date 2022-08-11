package br.com.project.shopstyle.mscustomer.dto;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerFORM {

    @CPF
    private String cpf;
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    private Genre sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    @Email
    private String email;
    private String password;
    private boolean active;

}
