package br.com.project.shopstyle.mscatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String color;
    private String size;
    private Integer height;
    private Integer width;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skuId", referencedColumnName = "id")
    private List<Media> images;
    private Long productId;

}
