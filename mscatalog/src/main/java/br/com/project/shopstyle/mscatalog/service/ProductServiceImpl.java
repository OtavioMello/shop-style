package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.ProductDTO;
import br.com.project.shopstyle.mscatalog.dto.ProductGetByIdDTO;
import br.com.project.shopstyle.mscatalog.entity.Category;
import br.com.project.shopstyle.mscatalog.entity.Product;
import br.com.project.shopstyle.mscatalog.repository.CategoryRepository;
import br.com.project.shopstyle.mscatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public URI postProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));

        categoryIsValid(category);

        Product product = productRepository.save(modelMapper.map(productDTO, Product.class));
        category.getProducts().add(product);
        categoryRepository.save(category);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(product.getId());
    }

    @Override
    public Page<ProductDTO> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public ProductGetByIdDTO getProductById(Long id) {
        return modelMapper.map(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND")), ProductGetByIdDTO.class);
    }

    @Override
    public ProductDTO updateProductById(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));

        categoryIsValid(category);

        if (product.getCategoryId() != category.getId()){
            Optional<Category> oldCategory = categoryRepository.findById(product.getCategoryId());
            oldCategory.get().getProducts().remove(product);
            categoryRepository.save(oldCategory.get());
        }

        product = modelMapper.map(productDTO, Product.class);
        product.setId(id);
        productRepository.save(product);

        category.getProducts().add(product);
        categoryRepository.save(category);

        return modelMapper.map(product, ProductDTO.class);

    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        Optional<Category> category = categoryRepository.findById(product.getCategoryId());

        category.get().getProducts().remove(product);
        categoryRepository.save(category.get());

        productRepository.delete(product);
    }

    private boolean categoryIsValid(Category category){

        if (!category.isActive()){
            throw new RuntimeException("CATEGORY IS NOT ACTIVE");
        }
        if (!categoryRepository.findAllByParentId(category.getId()).isEmpty()){
            throw new RuntimeException("INVALID CATEGORY");
        }
        return true;
    }
}
