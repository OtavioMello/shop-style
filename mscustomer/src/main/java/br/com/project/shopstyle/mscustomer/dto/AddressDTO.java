package br.com.project.shopstyle.mscustomer.dto;

import br.com.project.shopstyle.mscustomer.constants.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class AddressDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private State state;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String cep;
    private String complement;
    @NotNull
    private Long customerId;

}
