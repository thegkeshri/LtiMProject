package com.ltim.product.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ltim.product.entity.Product;
import com.ltim.product.repository.ProductRepo;
import com.ltim.product.service.ProductService;

import jakarta.persistence.EntityManager;

@Service
public class ProductServiceImpl implements ProductService {
	
	Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	EntityManager em;
	
	@Autowired
    private ProductRepo productRepository;

    @Override
    public List<Product> getAllProducts() {
		 logger.trace("Inside method getAllProducts of ProductServiceImpl");

    	List<Product> list= productRepository.findAll();
    	logger.trace("Products available are:"+list);
    	return list;
    }

    @Override
    public Product getProductById(Integer id) {
		 logger.trace("Inside method getProductById of ProductServiceImpl");

    	Product p= productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
		 logger.trace("exit from method getProductById of ProductServiceImpl");

    	return p;
    }

    @Override
    public Product createProduct(Product product) {
    	 try {
    		 
    		 logger.trace("Inside method createProduct of ProductServiceImpl");
    		 
             Product p= productRepository.save(product);
             
             logger.trace("Product added with Name: "+p.getName());
    		 logger.trace("exit from method createProduct of ProductServiceImpl");

             return p;
         } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save product");
         }
    }

    @Override
    @Transactional
    public Product updateProduct(Integer id, Product updatedProduct) {
    	
		logger.trace("Inside method updateProduct of ProductServiceImpl with productId:" + id);
		try {

			Product p = em.find(Product.class, id);

			if (p != null) {
				 if (updatedProduct.getDescription() != null) {
				        p.setDescription(updatedProduct.getDescription());
				    }
				    if (updatedProduct.getName() != null) {
				        p.setName(updatedProduct.getName());
				    }
				    if (updatedProduct.getPrice() != null) {
				        p.setPrice(updatedProduct.getPrice());
				    }
				    if (updatedProduct.getQuantity() != null) {
				        p.setQuantity(updatedProduct.getQuantity());
				    }
				em.merge(p);
				return p;
			} else
				logger.trace("Product not exist with id: " + id);

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}

    }

    @Override
    public String deleteProduct(Integer id) {
      	 logger.trace("Inside method deleteProduct of ProductServiceImpl with productId:"+id);

    	
    	 if (!productRepository.existsById(id)) {
    		 	logger.trace("Product not exist with id: "+ id);
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
         }
         productRepository.deleteById(id);
      	 logger.trace("Product deleted successfully  with productId:"+id);

         return "Product Deleted Successfully!!";
    }
}
