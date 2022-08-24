package br.com.project.shopstyle.mscatalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryGetDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private Boolean active;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryGetDTO> children;

}
