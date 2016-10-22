package com.powerhaus.brookleaf.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static org.apache.commons.lang3.builder.ToStringBuilder.*;
import static org.apache.commons.lang3.builder.ToStringStyle.*;

@Embeddable
public class Address {
    
    @Column
    private String line;
    
    @Column
    private String city;
    
    @Column(name = "post_code")
    private String postCode;
    
    @Column
    private String country;
    
    @Column
    private String zone;
    
    private Address(Builder builder) {
        this.line = builder.line;
        this.city = builder.city;
        this.postCode = builder.postCode;
        this.country = builder.country;
        this.zone = builder.zone;
    }
    
    protected Address() { }
    
    public static class Builder {
        private String line;
        private String city;
        private String postCode;
        private String country;
        private String zone;
        
        public Builder withLine(String line) {
            this.line = line;
            return this;
        }
    
        public Builder withCity(String city) {
            this.city = city;
            return this;
        }
    
        public Builder withPostcode(String postCode) {
            this.postCode = postCode;
            return this;
        }
    
        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }
    
        public Builder withZone(String zone) {
            this.zone = zone;
            return this;
        }
    
        public Address build() {
            return new Address(this);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(Address address) {
        Builder builder = new Builder();
        
        builder.line = address.line;
        builder.city = address.city;
        builder.country = address.country;
        builder.postCode = address.postCode;
        builder.zone = address.zone;
        
        return builder;
    }
    
    
    public String getLine() {
        
        return line;
    }
    
    public String getCity() {
        
        return city;
    }
    
    public String getPostCode() {
        
        return postCode;
    }
    
    public String getCountry() {
        
        return country;
    }
    
    public String getZone() {
        return zone;
    }
    
    @Override
    public boolean equals(Object o) {
        
        return EqualsBuilder.reflectionEquals(this, o);
    }
    
    @Override
    public int hashCode() {
        
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public String toString() {
        
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
