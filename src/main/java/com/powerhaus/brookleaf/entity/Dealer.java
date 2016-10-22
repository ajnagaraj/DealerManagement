package com.powerhaus.brookleaf.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.*;
import static org.apache.commons.lang3.builder.ToStringStyle.*;

@Entity
@Table(name = "dealer")
public class Dealer {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String name;
    
    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String email;
    
    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String phone;
    
    @Column
    private String associate;
    
    @Embedded
    private Address address;
    
    @ManyToMany(cascade = PERSIST)
    @JoinTable(
            name = "dealer_product",
            joinColumns = {@JoinColumn(name = "dealer_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products;
    
    protected Dealer() {
        products = new HashSet<Product>();
    }
    
    private Dealer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.associate = builder.associate;
        this.products = builder.products;
    }
    
    public static class Builder {
        private String name;
        private String email;
        private String phone;
        
        private String associate;
        private Address address;
        private Long id;
    
        private Set<Product> products;
    
        private Builder() {
            products = new HashSet<>();
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder withAssociate(String associate) {
            this.associate = associate;
            return this;
        }
        
        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }
        
        public Builder addProducts(Collection<Product> products) {
            this.products.addAll(products);
            return this;
        }
        
        public Dealer build() {
            return new Dealer(this);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(Dealer dealer) {
        Builder builder = new Builder();
        
        builder.name = dealer.name;
        builder.email = dealer.email;
        builder.phone = dealer.phone;
        builder.address = dealer.address;
        builder.associate = dealer.associate;
        builder.id = dealer.id;
        
        return builder;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAssociate() {
        return associate;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (!(o instanceof Dealer))
            return false;
        
        Dealer dealer = (Dealer) o;
    
        return new EqualsBuilder()
                .append(name, dealer.name)
                .append(email, dealer.email)
                .append(phone, dealer.phone)
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(email)
                .append(phone)
                .toHashCode();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("email", email)
                .append("phone", phone)
                .append("associate", associate)
                .append("address", address)
                .toString();
    }
}
