package com.powerhaus.brookleaf.service;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.exception.ServiceException;
import com.powerhaus.brookleaf.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.String.*;
import static org.apache.commons.lang3.Validate.*;

@Service
public class TransactionalDealerService implements DealerService {
    
    private DealerRepository dealerRepository;
    
    @Autowired
    public TransactionalDealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }
    
    @Override
    public List<Dealer> createDealers(List<Dealer> dealers) {
        notEmpty(dealers, "The dealers cannot be empty");
        
        Set<Dealer> uniqueDealers = new HashSet<>(dealers);
        
        try {
            return dealerRepository.save(uniqueDealers);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to create dealers", dataAccessFailure);
        }
        
    }
    
    @Override
    public Dealer createDealer(Dealer dealer) {
        notNull(dealer, "Invalid dealer. It cannot be null");
        
        try {
            return dealerRepository.save(dealer);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to create dealer", dataAccessFailure);
        }
    }
    
    @Override
    @Transactional
    public Dealer findDealer(Long id) {
        notNull(id, "Invalid dealer id. It cannot be null");
        
        try {
            Dealer dealer = dealerRepository.findOne(id);
            return Dealer.builder(dealer).build();
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealer", dataAccessFailure);
        }
    }
    
    @Override
    public Set<Dealer> findAllDealers() {
        try {
            Set<Dealer> dealers = new HashSet<>(dealerRepository.findAll());
            
            return dealers;
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealers by product name", dataAccessFailure);
        }
    }
    
    @Override
    public Set<Dealer> findDealersByProductName(String name) {
        notEmpty(name, "The name cannot be empty or null");
        
        try {
            return dealerRepository.findByProductName(name);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealers by product name", dataAccessFailure);
        }
        
    }
    
    @Override
    public Set<Dealer> findDealersByProductType(String type) {
        notEmpty(type, "The type cannot be empty or null");
        
        try {
            return dealerRepository.findByProductType(type);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealers by product type", dataAccessFailure);
        }
    }
    
    @Override
    public Set<Dealer> findDealersByZone(String zone) {
        notEmpty(zone, "The zone cannot be empty or null");
        
        try {
            return dealerRepository.findByAddressZone(zone);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealers by zone", dataAccessFailure);
        }
    }
    
    @Override
    public Set<Dealer> findDealersByName(String name) {
        notEmpty(name, "The name cannot be empty or null");
    
        try {
            return dealerRepository.findByName(name);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to find dealers by name", dataAccessFailure);
        }
    }
    
    @Override
    public List<Dealer> updateDealers(List<Dealer> dealers) {
        notEmpty(dealers, "The zone cannot be empty or null");
        
        boolean allUpdatableDealers = dealers.stream().allMatch(dealer -> dealer.getId() != null);
        if(!allUpdatableDealers) {
            throw new ServiceException(format("One or more dealers - %s are not updatable", dealers));
        }
        
        Set<Dealer> uniqueDealers = new HashSet<>(dealers);
        try {
            return dealerRepository.save(uniqueDealers);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to update dealers", dataAccessFailure);
        }
    }
    
    @Override
    public Dealer updateDealer(Dealer dealer) {
        notNull(dealer, "The dealer cannot be null");
        
        if(dealer.getId() == null) {
            throw new ServiceException(format("Dealer - %s is not updatable", dealer));
        }
    
        try {
            Dealer updatedDealer = Dealer.builder(dealerRepository.save(dealer)).build();
            return updatedDealer;
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to update dealer", dataAccessFailure);
        }
    }
    
    
    @Override
    public void deleteDealers(List<Dealer> dealers) {
        notEmpty(dealers, "The dealers cannot be empty or null");
    
        boolean allDeletableDealers = dealers.stream().allMatch(dealer -> dealer.getId() != null);
        if(!allDeletableDealers) {
            throw new ServiceException(format("One or more dealers - %s are not deletable", dealers));
        }
        
        Set<Dealer> uniqueDealers = new HashSet<>(dealers);
        try {
            dealerRepository.delete(uniqueDealers);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to delete dealers", dataAccessFailure);
        }
    }
    
    @Override
    public void deleteDealer(Dealer dealer) {
        notNull(dealer, "The dealer cannot be empty or null");
        
        if(dealer.getId() == null) {
            throw new ServiceException(format("Dealer - %s is not deletable", dealer));
        }
    
        try {
            dealerRepository.delete(dealer);
        } catch(DataAccessException dataAccessFailure) {
            throw new ServiceException("Failed to delete dealer", dataAccessFailure);
        }
    }
}
