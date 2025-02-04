package com.ltim.product.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ltim.product.entity.Product;
import com.ltim.product.repository.ProductRepo;
import com.ltim.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
    private ProductRepo productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
    	return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
    }

    @Override
    public Product createProduct(Product product) {
    	 try {
             Product p= productRepository.save(product);
             logger.trace("Product added with Name: "+p.getName());
             return p;
         } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save product");
         }
    }

    @Override
    public Product updateProduct(Integer id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setQuantity(updatedProduct.getQuantity());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public String deleteProduct(Integer id) {
    	 if (!productRepository.existsById(id)) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
         }
         productRepository.deleteById(id);
         return "Product Deleted Successfully!!";
    }
}
