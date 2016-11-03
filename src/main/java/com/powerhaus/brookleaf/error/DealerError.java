package com.powerhaus.brookleaf.error;

public class DealerError {
    
    private String nameError;
    private String emailError;
    private String phoneError;
    private String associateError;
    private AddressError addressError;
    
    public String getNameError() {
        return nameError;
    }
    
    public void setNameError(String nameError) {
        this.nameError = nameError;
    }
    
    public String getEmailError() {
        return emailError;
    }
    
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
    
    public String getPhoneError() {
        return phoneError;
    }
    
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
    
    public String getAssociateError() {
        return associateError;
    }
    
    public void setAssociateError(String associateError) {
        this.associateError = associateError;
    }
    
    public AddressError getAddressError() {
        return addressError;
    }
    
    public void setAddressError(AddressError addressError) {
        this.addressError = addressError;
    }
}
