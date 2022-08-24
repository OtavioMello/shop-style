package br.com.project.shopstyle.mscatalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean active;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDTO> children;
}
