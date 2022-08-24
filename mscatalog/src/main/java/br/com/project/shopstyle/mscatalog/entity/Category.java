package br.com.project.shopstyle.mscatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;
    private Long parentId;
    @OneToMany
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private List<Product> products = new ArrayList<>();
    @Transient
    private List<Category> children = new ArrayList<>();

}
