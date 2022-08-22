package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductGetByIdDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface ProductService {

    URI postProduct(ProductDTO productDTO);

    Page<ProductDTO> getProducts(Pageable pageable);

    ProductGetByIdDTO getProductById(Long id);

    ProductDTO updateProductById(Long id, ProductDTO productDTO);

    void deleteProductById(Long id);
}
