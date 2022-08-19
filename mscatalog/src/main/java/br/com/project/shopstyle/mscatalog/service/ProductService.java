package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface ProductService {

    URI postProduct(ProductDTO productDTO);
}
