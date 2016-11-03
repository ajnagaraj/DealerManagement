package com.powerhaus.brookleaf.form;

import com.powerhaus.brookleaf.entity.Address;
import com.powerhaus.brookleaf.entity.Dealer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class DealerForm {
    private static final String PHONE_PATTERN = "^[0-9\\-]*$";
    
    private Long id;
    
    @NotEmpty(message = "Please enter a name")
    private String name;
    
    @NotEmpty(message = "Please enter an email")
    private String email;
    
    @NotEmpty(message = "Please enter a phone number")
    @Pattern(message = "Invalid phone number", regexp = PHONE_PATTERN)
    private String phone;
    
    @NotEmpty(message = "Please associate a contact person")
    private String associate;
    
    private AddressForm address;
    
    public DealerForm() {}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAssociate() {
        return associate;
    }
    
    public void setAssociate(String associate) {
        this.associate = associate;
    }
    
    public AddressForm getAddress() {
        return address;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setAddress(AddressForm addressForm) {
        this.address = addressForm;
    }
    
    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
