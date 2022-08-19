package br.com.project.shopstyle.mscatalog.controller;

import br.com.project.shopstyle.mscatalog.dto.CategoryDTO;
import br.com.project.shopstyle.mscatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping
//    public ResponseEntity<List<CategoryWithChildDTO>> getCategories(){
//    } todo

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
