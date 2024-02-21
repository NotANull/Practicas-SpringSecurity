package com.notanull.SpringSecurityCourse.service.imp;

import com.notanull.SpringSecurityCourse.dto.request.SaveCategoryDto;
import com.notanull.SpringSecurityCourse.exceptions.ObjectNotFoundException;
import com.notanull.SpringSecurityCourse.persistence.entity.Category;
import com.notanull.SpringSecurityCourse.persistence.repository.ICategoryRepository;
import com.notanull.SpringSecurityCourse.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImp implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryServiceImp(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long categoryId) {
        return this.categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(SaveCategoryDto saveCategoryDto) {

        Category category = new Category();
        category.setName(saveCategoryDto.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategoryDto saveCategoryDto) {
        Optional<Category> categoryFromDB = this.categoryRepository.findById(categoryId);
        if (categoryFromDB.isEmpty()) {
            throw new ObjectNotFoundException("Category not found with id");
        }

        categoryFromDB.get().setName(saveCategoryDto.getName());

        return this.categoryRepository.save(categoryFromDB.get());
    }

    @Override
    public Category disableOneById(Long categoryId) {

        Optional<Category> categoryFromDB = this.categoryRepository.findById(categoryId);
        if (categoryFromDB.isEmpty()) {
            throw new ObjectNotFoundException("Category not found with id");
        }

        categoryFromDB.get().setStatus(Category.CategoryStatus.DISABLED);

        return this.categoryRepository.save(categoryFromDB.get());
    }
}
