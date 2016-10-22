package com.powerhaus.brookleaf.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Credentials {
    private static final String SIZE_ERROR = "must be between 5 and 36 characters long";
    
    @NotNull
    @Size(message=SIZE_ERROR, min=5, max=36)
    private String email;
    
    @NotNull
    @Size(message=SIZE_ERROR, min=5, max=36)
    private String password;
    
    public Credentials() { }
    
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        
        return email;
    }
    
    public String getPassword() {
        
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof Credentials)) {
            return false;
        }
        
        Credentials credentials = (Credentials) o;
    
        return email != null ? email.equals(credentials.email) : credentials.email == null;
    }
    
    @Override
    public int hashCode() {
        
        return email != null ? email.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
