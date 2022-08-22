package br.com.project.shopstyle.mscatalog.controller;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.dto.CategoryGetDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<URI> postCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        return ResponseEntity.created(categoryService.postCategory(categoryDTO)).build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoryGetDTO>> getCategories(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductDTO>> getProductsFromCategory(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(required = true) Long id){
        return ResponseEntity.ok(categoryService.getProductsFromCategory(pageable, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategoryById(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.updateCategoryById(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
