package br.com.project.shopstyle.mscatalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String brand;
    private String material;
    @NotNull
    private Boolean active;
    @NotNull
    private Long categoryId;

}
