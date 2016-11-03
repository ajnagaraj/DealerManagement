package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.config.DealerManagementRootConfiguration;
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
@ContextConfiguration(classes = DealerManagementRootConfiguration.class)
@ActiveProfiles("development")
public class DealerRepositoryTest {
    
    @Autowired
    private DealerRepository dealerRepository;
    
   @Test
    public void shouldSuccessfullyConnectToTheDatabase() {
        Set<Dealer> dealers = dealerRepository.findByName("weather");
        
        assertThat(dealers).isNotNull();
    }
    
    @Test
    public void shouldCreateDealer() {
        Address address = Address.builder()
                .withCity("Ninjack")
                .withLine("75 Breakdown")
                .withPostcode("BTS 007")
                .withCountry("BarnLand")
                .withZone("Inner Circle")
                .build();
    
        Dealer dealer = Dealer.builder()
                .withName("Cloud Brokers")
                .withEmail("cb@broking.com")
                .withPhone("982342")
                .withAddress(address)
                .withAssociate("mickey")
                .build();
        
        Dealer savedDealer = dealerRepository.save(dealer);
        
        assertThat(savedDealer != dealer);
        assertThat(savedDealer.getId()).isNotNull();
    }
    
    @Test
    public void shouldCreateDealerAndProduct() {
        Address address = Address.builder()
                .withCity("YellowDrear")
                .withLine("75 Breakdown")
                .withPostcode("BTS 007")
                .withCountry("BarnLand")
                .withZone("Inner Circle")
                .build();
    
        Product product = Product.builder()
                .withName("Bubble Soap")
                .withType("Cosmetic")
                .build();
        
        Dealer dealer = Dealer.builder()
                .withName("BogsForFree")
                .withEmail("freebogs@bogged.com")
                .withPhone("982342")
                .withAddress(address)
                .withAssociate("mickey")
                .addProduct(product)
                .build();
        
        Dealer savedDealer = dealerRepository.save(dealer);
        
        assertThat(savedDealer != dealer);
        assertThat(savedDealer.getId()).isNotNull();
        
        assertThat(savedDealer.getProducts()).containsExactly(product);
    }
    

    @Test
    public void shouldEditDealer() {
        Dealer dealer = dealerRepository.findOne(1L);
        
        assertThat(dealer.getEmail()).isEqualTo("arial@clean.com");
        
        Dealer editedDealer = Dealer.builder(dealer)
                .withEmail("arial@washing.co.uk")
                .build();
        
        Dealer updatedDealer = dealerRepository.save(editedDealer);
        
        assertThat(updatedDealer != editedDealer);
        assertThat(updatedDealer.getEmail()).isEqualTo("arial@washing.co.uk");
        
        assertThat(updatedDealer.getId()).isEqualTo(1L);
    }
    
    @Test
    public void shouldDeleteDealer() {
        // Setup
        Address address = Address.builder()
                .withCity("Buttischer")
                .withLine("99 Butts Lane")
                .withPostcode("PBP 8SS")
                .withCountry("Buttland")
                .withZone("Brown")
                .build();
    
        Product product = Product.builder()
                .withName("Bog Rolls")
                .withType("Emergency")
                .build();
    
        Dealer dealer = Dealer.builder()
                .withName("BogsWellMade")
                .withEmail("bogs@emergency.com")
                .withPhone("15231112")
                .withAddress(address)
                .withAssociate("mickey")
                .addProduct(product)
                .build();
        
        Dealer savedDealer = dealerRepository.save(dealer);
        
        assertThat(savedDealer.getId()).isNotNull();
        
        // Delete
        dealerRepository.delete(savedDealer);
        
        Dealer unknownDealer = dealerRepository.findOne(savedDealer.getId());
        
        assertThat(unknownDealer).isNull();
    }
    
    @Test
    public void shouldFindDealerByName() {
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        Set<Dealer> dealers = dealerRepository.findByName("Bog");
        
        assertThat(dealers).containsExactly(expectedDealer);
    }
    
    @Test
    @Transactional
    public void shouldLazilyFetchProductsOnTheDealer() {
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        Set<Dealer> dealers = dealerRepository.findByName("Bog");
        
        assertThat(dealers).containsExactly(expectedDealer);
    
        // Safe call to Optional.get as the collection was asserted for its contents
        Dealer retrievedDealer = dealers.stream().findFirst().get();
        
        Set<Product> associatedProducts = retrievedDealer.getProducts();
        
        Product expectedProduct = Product.builder()
                .withName("SoftBogRoll")
                .withType("Wipes")
                .build();
        
        assertThat(associatedProducts).containsExactly(expectedProduct);
    }
    
    @Test
    public void shouldFindDealerByAddress() {
        Address address = Address.builder()
                .withCity("Blight")
                .withLine("66 Valley Street")
                .withPostcode("PDT 9SN")
                .withCountry("Bleak Country")
                .withZone("Green")
                .build();
        
        Set<Dealer> dealers = dealerRepository.findByAddress(address);
    
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        assertThat(dealers).containsExactly(expectedDealer);
    }
    
    @Test
    public void shouldFindDealerByAddressZone() {
        Set<Dealer> dealers = dealerRepository.findByAddressZone("Green");
        
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        assertThat(dealers).containsExactly(expectedDealer);
    }
    
    @Test
    public void shouldFindDealerByPhone() {
        Set<Dealer> dealers = dealerRepository.findByPhone("234234123412");
        
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        assertThat(dealers).containsExactly(expectedDealer);
    }
    
    @Test
    public void shouldFindDealerByEmail() {
        Set<Dealer> dealers = dealerRepository.findByEmail("bog@softbogs.com");
        
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
        
        assertThat(dealers).containsExactly(expectedDealer);
    }
    
    @Test
    public void shouldFindDealersByProductName() {
        Set<Dealer> dealers = dealerRepository.findByProductName("SoftBogRoll");
    
        Dealer expectedDealer = Dealer.builder()
                .withName("Bog")
                .withEmail("bog@softbogs.com")
                .withPhone("234234123412")
                .build();
    
        assertThat(dealers).containsExactly(expectedDealer);
    }
}
