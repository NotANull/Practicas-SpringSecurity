package com.notanull.SpringSecurityCourse.persistence.repository;

import com.notanull.SpringSecurityCourse.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
