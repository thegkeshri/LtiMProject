package com.ltim.product.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ltim.product.repository.ProductRepo;
import com.ltim.product.service.ProductService;
import com.ltim.product.service.impl.ProductServiceImpl;

public class ProductServiceTest {

	 @Mock
	    private ProductRepo productRepository;

	    @InjectMocks
	    private ProductServiceImpl productService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCreateProduct() {
	        Product product = new Product();
	        product.setName("Test Product");
	        product.setDescription("Test Description");
	        product.setPrice(100.0);
	        product.setQuantity(10);

	        when(productRepository.save(product)).thenReturn(product);

	        Product createdProduct = productService.createProduct(product);

	        assertEquals("Test Product", createdProduct.getName());
	        assertEquals("Test Description", createdProduct.getDescription());
	        assertEquals(100.0, createdProduct.getPrice());
	        assertEquals(10, createdProduct.getQuantity());
	        verify(productRepository, times(1)).save(product);
	    }

//	    @Test
//	    public void testGetProductById() {
//	        Product product = new Product();
//	        product.setId((int) 1L);
//	        product.setName("Test Product");
//	        product.setDescription("Test Description");
//	        product.setPrice(100.0);
//	        product.setQuantity(10);
//
//	        when(productRepository.findById((int) 1L)).thenReturn(Optional.of(product));
//
//	        Product foundProduct = productService.getProductById((int) 1L);
//
//	        assertEquals("Test Product", foundProduct.getName());
//	        assertEquals("Test Description", foundProduct.getDescription());
//	        assertEquals(100.0, foundProduct.getPrice());
//	        assertEquals(10, foundProduct.getQuantity());
//	        verify(productRepository, times(1)).findById((int) 1L);
//	    }

	    @Test
	    public void testGetAllProducts() {
	        Product product1 = new Product();
	        product1.setName("Product 1");
	        product1.setDescription("Description 1");
	        product1.setPrice(50.0);
	        product1.setQuantity(5);

	        Product product2 = new Product();
	        product2.setName("Product 2");
	        product2.setDescription("Description 2");
	        product2.setPrice(150.0);
	        product2.setQuantity(15);

	        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

	        List<Product> products = productService.getAllProducts();

	        assertEquals(2, products.size());
	        verify(productRepository, times(1)).findAll();
	    }

	    @Test
	    public void testUpdateProduct() {
	        Product product = new Product();
	        product.setId((int) 1L);
	        product.setName("Updated Product");
	        product.setDescription("Updated Description");
	        product.setPrice(200.0);
	        product.setQuantity(20);

	        when(productRepository.save(product)).thenReturn(product);

	        Product updatedProduct = productService.updateProduct(product.getId(), product);

	        assertEquals("Updated Product", updatedProduct.getName());
	        assertEquals("Updated Description", updatedProduct.getDescription());
	        assertEquals(200.0, updatedProduct.getPrice());
	        assertEquals(20, updatedProduct.getQuantity());
	        verify(productRepository, times(1)).save(product);
	    }

//	    @Test
//	    public void testDeleteProduct() {
//	        int productId = (int) 1L;
//
//	        doNothing().when(productRepository).deleteById(productId);
//
//	        productService.deleteProduct(productId);
//
//	        verify(productRepository, times(1)).deleteById(productId);
//	    }
}
