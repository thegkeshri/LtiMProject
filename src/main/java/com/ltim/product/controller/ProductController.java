package com.ltim.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltim.product.entity.Product;
import com.ltim.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	 @Autowired
	    private ProductService productService;  // Using the service interface

	    @GetMapping("/getAllProducts")
	    public ResponseEntity<List<Product>> getAllProducts() {
	        return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
	    }

	    @GetMapping("/getProductById/{id}")
	    public ResponseEntity<Product> getProductById(@RequestParam Integer id) {
	        return new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
	    }

	    @PostMapping("/createProduct")
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        return new ResponseEntity<Product>(productService.createProduct(product),HttpStatus.CREATED);
	    }

	    @PutMapping("/updateProduct/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
	        return new ResponseEntity<Product>(productService.updateProduct(id, updatedProduct),HttpStatus.OK);
	    }

	    @DeleteMapping("/deleteProduct/{id}")
	    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
	        return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.OK);
	    }
}
