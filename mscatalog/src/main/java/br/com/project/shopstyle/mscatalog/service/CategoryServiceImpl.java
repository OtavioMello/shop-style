package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import br.com.project.shopstyle.mscatalog.entity.Category;
import br.com.project.shopstyle.mscatalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postCategory(CategoryDTO categoryDTO) {

        parentCategoryValidation(categoryDTO);

        Category category = categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(category.getId());
    }

    @Override
    public List<CategoryGetDTO> getCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<Category> isChildren = new ArrayList<>();
        List<Category> categoriesWithChildren = new ArrayList<>();

        categories.forEach(c -> {
                List<Category> childrenCategories = categoryRepository.findAllByParentId(c.getId());
                c.getChildren().addAll(childrenCategories);
                isChildren.addAll(childrenCategories);
        });
        
        categories.forEach(c -> {
            if (!isChildren.contains(c)){
                categoriesWithChildren.add(c);
            }
        });
        return categoriesWithChildren.stream().map(c -> modelMapper.map(c, CategoryGetDTO.class)).toList();
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

    private boolean categoryHaveChild(Category c) {
        Optional<Category> optionalCategory = categoryRepository.findByParentId(c.getId());

        if (optionalCategory.isPresent()){
            return true;
        }
        return false;
    }
}
