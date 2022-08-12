package br.com.project.shopstyle.mscustomer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdatePasswordFORM {

    @NotNull
    private String password;

}
