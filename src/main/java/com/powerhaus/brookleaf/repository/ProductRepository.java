package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Set<Product> findByName(String name);
    Set<Product> findByType(String type);
}
