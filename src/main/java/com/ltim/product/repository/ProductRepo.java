package com.ltim.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltim.product.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
