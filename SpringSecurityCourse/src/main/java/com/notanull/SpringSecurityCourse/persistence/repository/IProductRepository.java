package com.notanull.SpringSecurityCourse.persistence.repository;

import com.notanull.SpringSecurityCourse.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
