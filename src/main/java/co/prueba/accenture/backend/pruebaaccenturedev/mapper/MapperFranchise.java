package co.prueba.accenture.backend.pruebaaccenturedev.mapper;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateFranchaiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.FranchiseResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Franchise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MapperFranchise {
    private final  MapperBranch  mapperBranch;

    public Franchise toEntity (CreateFranchaiseRequest request){
        if (request == null) return null;

        Franchise franchise = new Franchise();
        franchise.setName(request.getName());
        return franchise;
    }

    public FranchiseResponse toResponse (Franchise franchise){
        if (franchise == null) return null;
        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(
                        franchise.getBranchList() != null
                        ? franchise.getBranchList()
                                .stream()
                                .map(mapperBranch ::toResponseWhithoutProducts)
                                .toList() : Collections.emptyList()
                )
                .build();
    }

    public FranchiseResponse toResponseWhitBranchesAndProducts(Franchise franchise){
        if (franchise == null) return null;

        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(
                        franchise.getBranchList() != null
                        ?franchise.getBranchList()
                                .stream()
                                .map(mapperBranch::toResponse)
                                .collect(Collectors.toList()) :  new ArrayList<>()
                )
                .build();
    }
}
