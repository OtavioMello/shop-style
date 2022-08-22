package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.entity.Category;
import br.com.project.shopstyle.mscatalog.entity.Product;
import br.com.project.shopstyle.mscatalog.repository.CategoryRepository;
import br.com.project.shopstyle.mscatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postCategory(CategoryDTO categoryDTO) {

        parentCategoryValidation(categoryDTO);

        Category category = categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(category.getId());
    }

    @Override
    public Page<CategoryGetDTO> getCategories(Pageable pageable) {

        List<Category> categories = categoryRepository.findAll();
        List<Category> isChildren = new ArrayList<>();
        List<CategoryGetDTO> categoriesWithChildren = new ArrayList<>();

        categories.forEach(c -> {
                List<Category> childrenCategories = categoryRepository.findAllByParentId(c.getId());
                c.getChildren().addAll(childrenCategories);
                isChildren.addAll(childrenCategories);
        });

        categories.forEach(c -> {
            if (!isChildren.contains(c)){
                categoriesWithChildren.add(modelMapper.map(c, CategoryGetDTO.class));
            }
        });
        return new PageImpl<>(categoriesWithChildren, pageable, categoriesWithChildren.size());
    }

    @Override
    public Page<ProductDTO> getProductsFromCategory(Pageable pageable, Long id) {
        Page<Product> products = productRepository.findAllByCategoryId(id, pageable);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) {

        parentCategoryValidation(categoryDTO);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));
        category = modelMapper.map(categoryDTO, Category.class);
        category.setId(id);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));
        categoryRepository.delete(category);
    }



    private void parentCategoryValidation(CategoryDTO categoryDTO){
        if (categoryDTO.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("PARENT CATEGORY NOT FOUND"));

            if (!parentCategory.isActive()){
                throw new RuntimeException("PARENT CATEGORY IS INACTIVE");
            }
        }
    }
}
