package com.powerhaus.brookleaf.form;

import com.powerhaus.brookleaf.entity.Address;
import org.hibernate.validator.constraints.NotEmpty;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class AddressForm {
    
    @NotEmpty(message = "Please enter a street")
    private String street;
    
    @NotEmpty(message = "Please enter a city")
    private String city;
    
    @NotEmpty(message = "Please enter a postcode")
    private String postcode;
    
    @NotEmpty(message = "Please enter a zone")
    private String zone;
    
    @NotEmpty(message = "Please enter a country")
    private String country;
    
    public AddressForm() {}
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Address toAddress() {
        Address address = Address.builder()
                .withLine(getStreet())
                .withCity(getCity())
                .withPostcode(getPostcode())
                .withZone(getZone())
                .withCountry(getCountry())
                .build();
    
        return address;
    }
    
    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
