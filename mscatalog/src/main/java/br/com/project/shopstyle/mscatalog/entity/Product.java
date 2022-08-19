package br.com.project.shopstyle.mscatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private String material;
    private boolean active;
    private Long categoryId;
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Sku sku;

}
