package com.example.restapi.service;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private Logger LOG = LoggerFactory.getLogger(ProductsService.class);

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String id) {
        LOG.info("Getting the product with given id:" + id);
        return productRepository.findOne(id);
    }

    public Product saveProduct(Product product) {
        Product productToSave;
        try {
            LOG.info("Saving product...");
            productToSave = productRepository.save(product);
            return productToSave;
        } catch (Exception e) {
            LOG.error("An error occurred during product saving:" + e.getMessage());
        }
        return new Product();
    }

    public Product updateProduct(Product productToUpdate, String id) {
        Product foundProduct = productRepository.findOne(id);
        try {
            foundProduct.setName(productToUpdate.getName());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            return productRepository.save(foundProduct);
        } catch (Exception e) {
            LOG.error("An error pccurred during update of product" + e.getMessage());
        }
        return productToUpdate;
    }

    public void deleteProduct(String id) {
        try {
            productRepository.delete(id);
        } catch (Exception e) {
            LOG.error("An error occurred during deleting of product:" + e.getMessage());
        }
    }
}
