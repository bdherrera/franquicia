package co.prueba.accenture.backend.pruebaaccenturedev.service.impl;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateFranchaiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameFranchiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.FranchiseResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductMaxStockResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Franchise;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Product;
import co.prueba.accenture.backend.pruebaaccenturedev.mapper.MapperFranchise;
import co.prueba.accenture.backend.pruebaaccenturedev.mapper.MapperProduct;
import co.prueba.accenture.backend.pruebaaccenturedev.repository.FranchiseRepository;
import co.prueba.accenture.backend.pruebaaccenturedev.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FranchiseServiceImpl  implements FranchiseService {


    private final FranchiseRepository franchiseRepository;
    private final MapperFranchise mapperFranchise;
    private final MapperProduct mapperProduct;

    @Override
    public FranchiseResponse createFranchise(CreateFranchaiseRequest request) {
        Franchise  franchise = mapperFranchise.toEntity(request);
        franchise = franchiseRepository.save(franchise);
        return mapperFranchise.toResponse(franchise);
    }

    @Override
    public FranchiseResponse updateForName(Long franchiseId, UpdateNameFranchiseRequest request) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new IllegalArgumentException("Franchise not found"));
        franchise.setName(request.getName());
        franchise = franchiseRepository.save(franchise);
        return mapperFranchise.toResponse(franchise);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductMaxStockResponse> getProductMaxStock(Long franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new IllegalArgumentException("Franchise not found"));
        List<ProductMaxStockResponse> response = new ArrayList<>();
        if (franchise.getBranchList() == null || franchise.getBranchList().isEmpty()) {
            return response;
        }
        for (Branch branch : franchise.getBranchList()) {
            if(branch.getProducts() == null || branch.getProducts().isEmpty()) {
                continue;
            }
            List<Product> products = branch.getProducts();
            if (products == null || products.isEmpty()) {
                continue;
            }
            Product productMax = products.get(0);
            for (Product product : products) {
                if (product.getStock() > productMax.getStock()) {
                    productMax = product;
                }
                else if (product.getStock().equals(productMax.getStock())) {
                    if (product.getId()>productMax.getId()) {
                        productMax = product;
                    }
                }
            }
            ProductMaxStockResponse dto =mapperProduct.toResponseMaxStock(productMax);
            response.add(dto);
        }
        return response;
    }
}
