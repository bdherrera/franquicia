package co.prueba.accenture.backend.pruebaaccenturedev.service;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateFranchaiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameFranchiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.FranchiseResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductMaxStockResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Franchise;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FranchiseService {

    FranchiseResponse createFranchise(CreateFranchaiseRequest request);
    FranchiseResponse updateForName(Long franchiseId, UpdateNameFranchiseRequest request);
    List<ProductMaxStockResponse> getProductMaxStock(Long franchiseId);

}
