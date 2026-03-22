package co.prueba.accenture.backend.pruebaaccenturedev.repository;

import co.prueba.accenture.backend.pruebaaccenturedev.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBranch_Id(Long id);
}
