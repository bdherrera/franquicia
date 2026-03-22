package co.prueba.accenture.backend.pruebaaccenturedev.mapper;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.BranchResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperBranch {

    private final MapperProduct mapperProduct;

    public Branch toEntity(CreateBranchRequest request){
        if (request == null) return null;

        Branch branch = new Branch();
        branch.setName(request.getName());

        return branch;
    }

    public BranchResponse toResponse(Branch branch){
        if (branch == null) return null;

        BranchResponse branchResponse = new BranchResponse();

        return branchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchise() != null ? branch.getId() :null)
                .franchiseName(branch.getFranchise() !=null ?branch.getFranchise().getName() :null)
                .products(
                        branch.getProducts() != null ? branch.getProducts()
                                .stream()
                                .map(mapperProduct ::toResponse)
                                .collect(Collectors.toList()) :new ArrayList<>()
                )
                .build();
    }

    public BranchResponse toResponseWhithoutProducts(Branch branch){
        if (branch == null) return null;
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchise() != null ? branch.getFranchise().getId() : null)
                .franchiseName(branch.getFranchise() != null ? branch.getFranchise().getName() :null)
                .products(new ArrayList<>())
                .build();
    }






}
