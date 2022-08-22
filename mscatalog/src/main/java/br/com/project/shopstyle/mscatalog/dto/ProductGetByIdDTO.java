package br.com.project.shopstyle.mscatalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductGetByIdDTO {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private String material;
    private boolean active;
    private Long categoryId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SkuDTO> skus;

}
