package br.com.project.shopstyle.mscatalog.service;

import br.com.project.shopstyle.mscatalog.dto.SkuDTO;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public interface SkuService {
    URI postSku(SkuDTO skuDTO);

    SkuDTO updateSkuById(Long id, SkuDTO skuDTO);

    void deleteSkuById(Long id);
}
