package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.entity.Category;
import br.com.project.shopstyle.mscatalog.entity.Product;
import br.com.project.shopstyle.mscatalog.exception.BusinessException;
import br.com.project.shopstyle.mscatalog.repository.CategoryRepository;
import br.com.project.shopstyle.mscatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    public static final BusinessException CATEGORY_NOT_FOUND = new BusinessException(HttpStatus.NOT_FOUND.value(), "CATEGORY NOT FOUND");
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postCategory(CategoryDTO categoryDTO) {

        Category parentCategory = parentCategoryValidation(categoryDTO);
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);

        if(parentCategory != null){
            parentCategory.getChildren().add(category);
            categoryRepository.save(parentCategory);
        }

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

        Category parentCategory = parentCategoryValidation(categoryDTO);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> CATEGORY_NOT_FOUND);

        List<Category> childrenCategories = categoryRepository.findAllByParentId(category.getId());
        List<Product> products = productRepository.findAllByCategoryId(category.getId());

        if (parentCategory != null){
            if (parentCategory.getId() != category.getParentId()){
                Optional<Category> oldParent = categoryRepository.findById(category.getParentId());
                oldParent.get().getChildren().remove(category);
                categoryRepository.save(oldParent.get());
            }
        }

        category = modelMapper.map(categoryDTO, Category.class);
        category.setId(id);
        category.setChildren(childrenCategories);
        category.setProducts(products);

        updateChildren(category);
        categoryRepository.save(category);

        if (parentCategory != null){
            parentCategory.getChildren().add(category);
            categoryRepository.save(parentCategory);
        }
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> CATEGORY_NOT_FOUND);
        categoryRepository.delete(category);
    }

    private Category parentCategoryValidation(CategoryDTO categoryDTO){

        if (categoryDTO.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "PARENT CATEGORY NOT FOUND"));

            if (!parentCategory.getActive()){
                throw new BusinessException(HttpStatus.CONFLICT.value(),"PARENT CATEGORY IS INACTIVE");
            }
            return parentCategory;
        }
        return null;
    }

    private void updateChildren(Category category) {

        List<Category> childrenCategory = categoryRepository.findAllByParentId(category.getId());
        List<Product> products = productRepository.findAllByCategoryId(category.getId());

        if (!products.isEmpty()){
            products.forEach(p -> {
                p.setActive(Boolean.valueOf(category.getActive()));
                productRepository.save(p);
            });
        }

        if (!childrenCategory.isEmpty()){
            childrenCategory.forEach(c -> {
                c.setActive(Boolean.valueOf(category.getActive()));
                categoryRepository.save(c);
                updateChildren(c);
            });
        }
    }
}
