package co.prueba.accenture.backend.pruebaaccenturedev.service;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.BranchResponse;
import org.springframework.stereotype.Service;


public interface BranchService {
    BranchResponse createBranch(CreateBranchRequest request);
    BranchResponse updateNameBranch(Long id, UpdateNameBranchRequest request);
}
