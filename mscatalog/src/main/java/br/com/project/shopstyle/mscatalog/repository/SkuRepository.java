package br.com.project.shopstyle.mscatalog.repository;

import br.com.project.shopstyle.mscatalog.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {
}
