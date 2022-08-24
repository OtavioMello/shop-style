package br.com.project.shopstyle.mscustomer.dto;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerFORM {

    @CPF
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")
    private String cpf;
    @Size(min = 3, max = 50)
    @NotBlank
    private String firstName;
    @Size(min = 3, max = 70)
    @NotBlank
    private String lastName;
    @NotNull
    private Genre sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birthdate;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private boolean active;

}
