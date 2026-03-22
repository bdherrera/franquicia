package co.prueba.accenture.backend.pruebaaccenturedev.repository;

import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByFranchise_Id(Long id);
}
