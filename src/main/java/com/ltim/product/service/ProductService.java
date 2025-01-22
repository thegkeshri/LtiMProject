package com.ltim.product.service;

import java.util.List;

import com.ltim.product.entity.Product;


public interface ProductService {

	List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product createProduct(Product product);
    Product updateProduct(Integer id, Product updatedProduct);
    String deleteProduct(Integer id);
}
