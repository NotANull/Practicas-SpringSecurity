package com.notanull.SpringSecurityCourse.service;

import com.notanull.SpringSecurityCourse.dto.request.SaveCategoryDto;
import com.notanull.SpringSecurityCourse.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(SaveCategoryDto saveCategoryDto);

    Category updateOneById(Long categoryId, SaveCategoryDto saveCategoryDto);

    Category disableOneById(Long categoryId);
}
