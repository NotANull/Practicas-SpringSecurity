package com.notanull.SpringSecurityCourse.service.imp;

import com.notanull.SpringSecurityCourse.dto.request.SaveProductDto;
import com.notanull.SpringSecurityCourse.exceptions.ObjectNotFoundException;
import com.notanull.SpringSecurityCourse.persistence.entity.Category;
import com.notanull.SpringSecurityCourse.persistence.entity.Product;
import com.notanull.SpringSecurityCourse.persistence.repository.IProductRepository;
import com.notanull.SpringSecurityCourse.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImp implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImp(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Product createOne(SaveProductDto saveProductDto) {

        Product product = new Product();
        product.setName(saveProductDto.getName());
        product.setPrice(saveProductDto.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(saveProductDto.getCategoryId());

        product.setCategory(category);

        return this.productRepository.save(product);

    }

    @Override
    public Product updateOneById(Long productId, SaveProductDto saveProductDto) {

        Optional<Product> productFromDB = this.productRepository.findById(productId);

        if (productFromDB.isEmpty()) {
            throw new ObjectNotFoundException("Product not found with id");
        }

        productFromDB.get().setName(saveProductDto.getName());
        productFromDB.get().setPrice(saveProductDto.getPrice());

        Category category = new Category();
        category.setId(saveProductDto.getCategoryId());

        productFromDB.get().setCategory(category);

        return this.productRepository.save(productFromDB.get());
    }

    @Override
    public Product disableOneById(Long productId) {

        Optional<Product> productFromDB = this.productRepository.findById(productId);

        if (productFromDB.isEmpty()) {
            throw new ObjectNotFoundException("Product not found with id");
        }

        productFromDB.get().setStatus(Product.ProductStatus.DISABLED);

        return this.productRepository.save(productFromDB.get());
    }
}
