package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.config.DatabaseConfiguration;
import com.powerhaus.brookleaf.entity.Address;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ActiveProfiles("development")
public class ProductRepositoryTest {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void shouldSuccessfullyConnectToTheDatabase() {
        Set<Product> products = productRepository.findByName("Lazy Templates");
        
        assertThat(products).isNotNull();
    }
    
    @Test
    public void shouldCreateProduct() {
        Product product = Product.builder()
                .withName("Shine")
                .withType("Essentials")
                .build();
        
        Product savedProduct = productRepository.save(product);
        
        assertThat(savedProduct.getId()).isNotNull();
    }
    
    
    @Test
    public void shouldEditProduct() {
        Product product = productRepository.findOne(2L);
        
        assertThat(product.getName()).isEqualTo("Dash");
        
        Product editedProduct = Product.builder(product)
                .withType("Necessity")
                .build();
        
        Product updatedProduct = productRepository.save(editedProduct);
        
        assertThat(updatedProduct.getType()).isEqualTo("Necessity");
        assertThat(updatedProduct.getId()).isEqualTo(2L);
    }
    
    @Test
    public void shouldDeleteProduct() {
        // Setup
        Product product = Product.builder()
                .withName("Bog Rolls")
                .withType("Emergency")
                .build();
        
        Product savedProduct = productRepository.save(product);
        
        assertThat(savedProduct.getId()).isNotNull();
        
        // Delete
        productRepository.delete(savedProduct);
        
        Product unknownProduct = productRepository.findOne(savedProduct.getId());
        
        assertThat(unknownProduct).isNull();
    }
    
    @Test
    public void shouldFindProductByName() {
        Product expectedProduct = Product.builder()
                .withName("SoftBogRoll")
                .withType("Wipes")
                .build();
        
        Set<Product> products = productRepository.findByName("SoftBogRoll");
        
        assertThat(products).containsExactly(expectedProduct);
    }
    
    @Test
    @Transactional
    public void shouldLazilyFetchDealersOfTheProduct() {
        Product expectedProduct = Product.builder()
                .withName("SoftBogRoll")
                .withType("Wipes")
                .build();
    
        Set<Product> products = productRepository.findByName("SoftBogRoll");
    
        assertThat(products).containsExactly(expectedProduct);
        
        // Safe call to Optional.get as the collection was asserted for its contents
        Product retrievedProduct = products.stream().findFirst().get();
        
        Set<Dealer> associatedDealers = retrievedProduct.getDealers();
    
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        assertThat(associatedDealers).containsExactly(expectedDealer);
    }
    
    @Test
    public void shouldFindProductByType() {
        Product expectedProduct = Product.builder()
                .withName("SoftBogRoll")
                .withType("Wipes")
                .build();
    
        Set<Product> products = productRepository.findByType("Wipes");
    
        assertThat(products).containsExactly(expectedProduct);
    }
}
