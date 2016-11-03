package com.powerhaus.brookleaf.converter;

import com.powerhaus.brookleaf.error.AddressError;
import com.powerhaus.brookleaf.error.DealerError;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Component
public final class ErrorsToDealerErrorConverter implements Converter<Errors, DealerError> {
    
    @Override
    public DealerError convert(Errors errors) {
        
        DealerError dealerError = new DealerError();
        
        dealerError.setNameError(getErrorMessage(errors.getFieldError("name")));
        dealerError.setEmailError(getErrorMessage(errors.getFieldError("email")));
        dealerError.setPhoneError(getErrorMessage(errors.getFieldError("phone")));
        dealerError.setAssociateError(getErrorMessage(errors.getFieldError("associate")));
        
        AddressError addressError = new AddressError();
        addressError.setStreetError(getErrorMessage(errors.getFieldError("address.line")));
        addressError.setCityError(getErrorMessage(errors.getFieldError("address.city")));
        addressError.setPostcodeError(getErrorMessage(errors.getFieldError("address.postCode")));
        addressError.setCountryError(getErrorMessage(errors.getFieldError("address.country")));
        addressError.setZoneError(getErrorMessage(errors.getFieldError("address.zone")));
        
        dealerError.setAddressError(addressError);
        
        return dealerError;
    }
    
    private String getErrorMessage(FieldError error) {
        return error != null ? error.getDefaultMessage() : null;
    }
}
