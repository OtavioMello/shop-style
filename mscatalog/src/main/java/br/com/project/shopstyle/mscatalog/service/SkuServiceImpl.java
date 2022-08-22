package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.SkuDTO;
import br.com.project.shopstyle.mscatalog.entity.Media;
import br.com.project.shopstyle.mscatalog.entity.Product;
import br.com.project.shopstyle.mscatalog.entity.Sku;
import br.com.project.shopstyle.mscatalog.repository.ProductRepository;
import br.com.project.shopstyle.mscatalog.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.modelmapper.Converters.Collection.map;

@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService{

    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public URI postSku(SkuDTO skuDTO) {

        Product product = productIsValid(skuDTO.getProductId());

        Sku sku = skuRepository.save(modelMapper.map(skuDTO, Sku.class));
        product.getSkus().add(sku);
        productRepository.save(product);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(sku.getId());
    }

    @Override
    public SkuDTO updateSkuById(Long id, SkuDTO skuDTO) {

        Product product = productIsValid(skuDTO.getProductId());
        Sku sku = skuRepository.findById(id).orElseThrow(() -> new RuntimeException("SKU NOT FOUND"));

        if (sku.getProductId() != product.getId()){
            Optional<Product> oldProduct = productRepository.findById(product.getId());
            oldProduct.get().getSkus().remove(sku);
            productRepository.save(oldProduct.get());
        }

        sku = modelMapper.map(skuDTO, Sku.class);
        sku.setId(id);
        skuRepository.save(sku);

        product.getSkus().add(sku);
        productRepository.save(product);

        return modelMapper.map(sku, SkuDTO.class);

    }

    private Product productIsValid(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        if (!product.isActive()){
            throw new RuntimeException("PRODUCT IS INACTIVE");
        }
        return product;
    }
}
