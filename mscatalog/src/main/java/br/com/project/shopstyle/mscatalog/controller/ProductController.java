package br.com.project.shopstyle.mscatalog.controller;

import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductGetByIdDTO;
import br.com.project.shopstyle.mscatalog.service.ProductService;
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
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<URI> postProduct(@RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.created(productService.postProduct(productDTO)).build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetByIdDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProductById(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
