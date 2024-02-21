package com.notanull.SpringSecurityCourse.service;

import com.notanull.SpringSecurityCourse.dto.request.SaveProductDto;
import com.notanull.SpringSecurityCourse.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);

    Product createOne(SaveProductDto saveProductDto);

    Product updateOneById(Long productId, SaveProductDto saveProductDto);

    Product disableOneById(Long productId);
}
