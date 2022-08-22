package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface CategoryService {
    URI postCategory(CategoryDTO categoryDTO);

    Page<CategoryGetDTO> getCategories(Pageable pageable);

    Page<ProductDTO> getProductsFromCategory(Pageable pageable, Long id);

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);

}
