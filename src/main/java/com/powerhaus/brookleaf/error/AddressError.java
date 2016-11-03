package com.powerhaus.brookleaf.error;

public class AddressError {
    
    private String streetError;
    private String cityError;
    private String postcodeError;
    private String countryError;
    private String zoneError;
    
    public String getStreetError() {
        return streetError;
    }
    
    public void setStreetError(String streetError) {
        this.streetError = streetError;
    }
    
    public String getCityError() {
        return cityError;
    }
    
    public void setCityError(String cityError) {
        this.cityError = cityError;
    }
    
    public String getPostcodeError() {
        return postcodeError;
    }
    
    public void setPostcodeError(String postcodeError) {
        this.postcodeError = postcodeError;
    }
    
    public String getCountryError() {
        return countryError;
    }
    
    public void setCountryError(String countryError) {
        this.countryError = countryError;
    }
    
    public String getZoneError() {
        return zoneError;
    }
    
    public void setZoneError(String zoneError) {
        this.zoneError = zoneError;
    }
}
