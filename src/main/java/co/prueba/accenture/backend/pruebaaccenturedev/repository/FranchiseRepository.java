package co.prueba.accenture.backend.pruebaaccenturedev.repository;

import co.prueba.accenture.backend.pruebaaccenturedev.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository  extends JpaRepository<Franchise, Long> {
}
