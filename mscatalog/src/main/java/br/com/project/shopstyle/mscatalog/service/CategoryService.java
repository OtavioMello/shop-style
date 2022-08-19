package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface CategoryService {
    URI postCategory(CategoryDTO categoryDTO);

    //List<CategoryWithChildDTO> getCategories(); todo

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);
}
