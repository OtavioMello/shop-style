package br.com.project.shopstyle.mscatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;
    private Long parentId;
    @OneToMany
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private List<Product> products;

}
