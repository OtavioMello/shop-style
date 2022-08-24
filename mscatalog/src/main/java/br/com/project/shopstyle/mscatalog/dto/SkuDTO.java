package br.com.project.shopstyle.mscatalog.dto;

import br.com.project.shopstyle.mscatalog.entity.Media;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SkuDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String color;
    @NotEmpty
    private String size;
    @NotNull
    private Integer height;
    @NotNull
    private Integer width;
    @NotEmpty
    private List<MediaDTO> images;
    @NotNull
    private Long productId;

}
