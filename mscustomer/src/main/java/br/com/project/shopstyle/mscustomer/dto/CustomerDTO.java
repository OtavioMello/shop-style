package br.com.project.shopstyle.mscustomer.dto;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {

    private String cpf;
    private String firstName;
    private String lastName;
    private Genre sex;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    private String email;
    private boolean active;
}
