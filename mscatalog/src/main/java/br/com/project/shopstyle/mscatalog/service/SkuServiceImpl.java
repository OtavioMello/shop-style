package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.SkuDTO;
import br.com.project.shopstyle.mscatalog.entity.Media;
import br.com.project.shopstyle.mscatalog.entity.Product;
import br.com.project.shopstyle.mscatalog.entity.Sku;
import br.com.project.shopstyle.mscatalog.exception.BusinessException;
import br.com.project.shopstyle.mscatalog.repository.MediaRepository;
import br.com.project.shopstyle.mscatalog.repository.ProductRepository;
import br.com.project.shopstyle.mscatalog.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService{

    public static final BusinessException SKU_NOT_FOUND = new BusinessException(HttpStatus.NOT_FOUND.value(), "SKU NOT FOUND");
    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;

    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;
    @Override
    public URI postSku(SkuDTO skuDTO) {

        Product product = productIsValid(skuDTO.getProductId());
        List<Media> images = skuDTO.getImages().stream().map(i -> modelMapper.map(i, Media.class)).toList();
        mediaRepository.saveAll(images);
        Sku sku = modelMapper.map(skuDTO, Sku.class);
        sku.setImages(images);
        skuRepository.save(sku);
        product.getSkus().add(sku);
        productRepository.save(product);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(sku.getId());
    }

    @Override
    public SkuDTO updateSkuById(Long id, SkuDTO skuDTO) {

        Sku sku = getSku(id);
        Product product = productIsValid(skuDTO.getProductId());
        mediaRepository.deleteAll(sku.getImages());

        if (sku.getProductId() != product.getId()){
            Optional<Product> oldProduct = productRepository.findById(product.getId());
            oldProduct.get().getSkus().remove(sku);
            productRepository.save(oldProduct.get());
        }

        List<Media> images = skuDTO.getImages().stream().map(i -> modelMapper.map(i, Media.class)).toList();
        mediaRepository.saveAll(images);

        sku = modelMapper.map(skuDTO, Sku.class);
        sku.setId(id);
        sku.setImages(images);
        skuRepository.save(sku);

        product.getSkus().add(sku);
        productRepository.save(product);

        return modelMapper.map(sku, SkuDTO.class);

    }

    @Override
    public void deleteSkuById(Long id) {

        Sku sku = getSku(id);
        Optional<Product> product = productRepository.findById(sku.getProductId());

        product.get().getSkus().remove(sku);
        productRepository.save(product.get());

        skuRepository.delete(sku);
    }

    private Product productIsValid(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        if (!product.getActive()){
            throw new RuntimeException("PRODUCT IS INACTIVE");
        }
        return product;
    }

    private Sku getSku(Long id) {
        return skuRepository.findById(id).orElseThrow(() -> SKU_NOT_FOUND);
    }
}
