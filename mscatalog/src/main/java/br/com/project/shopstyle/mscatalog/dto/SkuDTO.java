package br.com.project.shopstyle.mscatalog.dto;

import br.com.project.shopstyle.mscatalog.entity.Media;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SkuDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String color;
    private String size;
    private Integer height;
    private Integer width;
    private List<MediaDTO> images;
    private Long productId;

}
