package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public interface CategoryService {
    URI postCategory(CategoryDTO categoryDTO);

    List<CategoryGetDTO> getCategories();

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);
}
