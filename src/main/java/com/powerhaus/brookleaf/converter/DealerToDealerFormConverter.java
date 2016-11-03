package com.powerhaus.brookleaf.converter;

import com.powerhaus.brookleaf.entity.Address;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.form.AddressForm;
import com.powerhaus.brookleaf.form.DealerForm;
import org.apache.commons.lang3.Validate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.Validate.*;

@Component
public class DealerToDealerFormConverter implements Converter<Dealer, DealerForm> {
    
    @Override
    public DealerForm convert(Dealer dealer) {
        notNull(dealer, "Cannot convert a null dealer");
        notNull(dealer.getAddress(), "Cannot convert a null address in dealer");
        
        DealerForm dealerForm = new DealerForm();
        dealerForm.setId(dealer.getId());
        dealerForm.setName(dealer.getName());
        dealerForm.setEmail(dealer.getEmail());
        dealerForm.setPhone(dealer.getPhone());
        dealerForm.setAssociate(dealer.getAssociate());
    
        Address address = dealer.getAddress();
        AddressForm addressForm = new AddressForm();
        addressForm.setStreet(address.getLine());
        addressForm.setCity(address.getCity());
        addressForm.setCountry(address.getCountry());
        addressForm.setPostcode(address.getPostCode());
        addressForm.setZone(address.getZone());
        
        dealerForm.setAddress(addressForm);
        
        return dealerForm;
    }
}
