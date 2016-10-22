package com.powerhaus.brookleaf.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

import static org.apache.commons.lang3.builder.ToStringBuilder.*;
import static org.apache.commons.lang3.builder.ToStringStyle.*;

@Entity
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NaturalId(mutable = true)
    private String name;
    
    @NaturalId(mutable = true)
    private String type;
    
    @ManyToMany(mappedBy = "products")
    private Set<Dealer> dealers;
    
    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
    }
    
    protected Product() { }
    
    public static class Builder {
        private String name;
        private String type;
        
        private Long id;
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withType(String type) {
            this.type = type;
            return this;
        }
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Product build() {
            return new Product(this);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(Product product) {
        Builder builder = new Builder();
        
        builder.name = product.name;
        builder.type = product.type;
        builder.id = product.id;
        
        return builder;
    }
    
    public Long getId() {
        
        return id;
    }
    
    public String getName() {
        
        return name;
    }
    
    public String getType() {
        
        return type;
    }
    
    public Set<Dealer> getDealers() {
        
        return Collections.unmodifiableSet(dealers);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (!(o instanceof Product))
            return false;
        
        Product product = (Product) o;
        
        return new EqualsBuilder()
                .append(name, product.name)
                .append(type, product.type)
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(type)
                .toHashCode();
    }
    
    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
