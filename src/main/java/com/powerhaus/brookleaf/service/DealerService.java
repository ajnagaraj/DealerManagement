package com.powerhaus.brookleaf.service;

import com.powerhaus.brookleaf.entity.Dealer;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface DealerService {
    List<Dealer> createDealers(List<Dealer> dealers);
    Dealer createDealer(Dealer dealer);
    
    Dealer findDealer(Long id);
    Set<Dealer> findAllDealers();
    Set<Dealer> findDealersByProductName(String name);
    Set<Dealer> findDealersByProductType(String type);
    Set<Dealer> findDealersByZone(String zone);
    Set<Dealer> findDealersByName(String name);
    
    List<Dealer> updateDealers(List<Dealer> dealers);
    Dealer updateDealer(Dealer dealer);
    
    void deleteDealers(List<Dealer> dealers);
    void deleteDealer(Dealer dealer);
}
