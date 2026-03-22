package co.prueba.accenture.backend.pruebaaccenturedev.service.impl;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateStockProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Product;
import co.prueba.accenture.backend.pruebaaccenturedev.exception.BusinessException;
import co.prueba.accenture.backend.pruebaaccenturedev.exception.ResourceNotFoundException;
import co.prueba.accenture.backend.pruebaaccenturedev.mapper.MapperProduct;
import co.prueba.accenture.backend.pruebaaccenturedev.repository.BranchRepository;
import co.prueba.accenture.backend.pruebaaccenturedev.repository.ProductRepository;
import co.prueba.accenture.backend.pruebaaccenturedev.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final MapperProduct mapperProduct;


    @Override
    public ProductResponse createProduct(CreateProductRequest request) {

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"+request.getBranchId()));
        Product product = mapperProduct.toEntity(request);
        product.setBranch(branch);
        productRepository.save(product);
        return mapperProduct.toResponse(product);
    }

    @Override
    public ProductResponse updateNameProduct(Long id, UpdateNameProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"+id));
        product.setName(request.getName());
        product = productRepository.save(product);
        return mapperProduct.toResponse(product);
    }


    @Override
    public ProductResponse upddateStockProduct(Long id, UpdateStockProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"+id));
        if (request.getStock() < 0) {
            throw new BusinessException("Stock less than zero");
        }
        product.setStock(request.getStock());
        product = productRepository.save(product);
        return mapperProduct.toResponse(product);
    }

    @Override
    public void deleteProductBranch(Long branchId, Long productId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"+branchId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"+productId));
        if (product.getBranch() == null || !product.getBranch().getId().equals(branch.getId())) {
            throw new BusinessException("Branch does not belong to product");
        }
        productRepository.delete(product);

    }
}
