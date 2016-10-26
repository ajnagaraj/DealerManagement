package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.entity.Address;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    
    Set<Dealer> findByName(String name);
    Set<Dealer> findByPhone(String phone);
    Set<Dealer> findByEmail(String email);
    
    Set<Dealer> findByAddress(Address address);
    Set<Dealer> findByAddressZone(String zone);
    
    @Query("SELECT d FROM Dealer d JOIN FETCH d.products p WHERE p.name = :name")
    Set<Dealer> findByProductName(@Param("name") String name);
    
    @Query("SELECT d FROM Dealer d JOIN FETCH d.products p WHERE p.type = :type")
    Set<Dealer> findByProductType(@Param("type") String type);
}
