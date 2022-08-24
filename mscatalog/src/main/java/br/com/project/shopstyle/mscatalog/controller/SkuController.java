package br.com.project.shopstyle.mscatalog.controller;

import br.com.project.shopstyle.mscatalog.dto.SkuDTO;
import br.com.project.shopstyle.mscatalog.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/skus")
@RequiredArgsConstructor
public class SkuController {

    private final SkuService skuService;

    @PostMapping
    public ResponseEntity<URI> postSku(@RequestBody SkuDTO skuDTO){
        return ResponseEntity.created(skuService.postSku(skuDTO)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkuDTO> updateSkuById(@PathVariable Long id, @RequestBody @Valid SkuDTO skuDTO){
        return ResponseEntity.ok(skuService.updateSkuById(id, skuDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSkuById(@PathVariable Long id){
        skuService.deleteSkuById(id);
        return ResponseEntity.ok().build();
    }

}
