package co.prueba.accenture.backend.pruebaaccenturedev.service.impl;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.BranchResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Franchise;
import co.prueba.accenture.backend.pruebaaccenturedev.mapper.MapperBranch;
import co.prueba.accenture.backend.pruebaaccenturedev.repository.BranchRepository;
import co.prueba.accenture.backend.pruebaaccenturedev.repository.FranchiseRepository;
import co.prueba.accenture.backend.pruebaaccenturedev.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final MapperBranch mapperBranch;


    @Override
    public BranchResponse createBranch(CreateBranchRequest request) {
        Franchise franchise = franchiseRepository.findById(request.getFranchiseId())
                .orElseThrow(() -> new IllegalArgumentException("Franchise not found"));
        Branch branch = mapperBranch.toEntity(request);
        branch.setFranchise(franchise);
        branch = branchRepository.save(branch);
        return mapperBranch.toResponseWhithoutProducts(branch);
    }

    @Override
    public BranchResponse updateNameBranch(Long id, UpdateNameBranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));
        branch.setName(request.getName());
        return mapperBranch.toResponseWhithoutProducts(branch);
    }
}
