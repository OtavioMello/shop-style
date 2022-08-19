package br.com.project.shopstyle.mscatalog.repository;

import br.com.project.shopstyle.mscatalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    List<Category> findAllByParentId(Long id);

    Optional<Category> findByParentId(Long id);
}
