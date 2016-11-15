package com.powerhaus.brookleaf.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@Entity
public class Note {
    private static final String NOT_EMPTY_ERROR = "cannot be empty";
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    @NotEmpty(message = NOT_EMPTY_ERROR)
    private String time;
    
    @Column(nullable = false)
    @NotEmpty(message = NOT_EMPTY_ERROR)
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;
    
    Note() {}
    
    private Note(Builder builder) {
        this.id = builder.id;
        this.time = builder.time;
        this.text = builder.text;
        this.dealer = builder.dealer;
    }
    
    public static class Builder {
        private Long id;
        private String time;
        private String text;
        private Dealer dealer;
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder withTime(String time) {
            this.time = time;
            return this;
        }
        
        public Builder withText(String text) {
            this.text = text;
            return this;
        }
        
        public Builder withDealer(Dealer dealer) {
            this.dealer = dealer;
            return this;
        }
        
        public Note build() {
            return new Note(this);
        }
    }
    
    public static Builder builder(Note note) {
        Builder builder = new Builder();
        builder.id = note.id;
        builder.time = note.time;
        builder.text = note.text;
        builder.dealer = Dealer.builder(note.dealer).build();
        
        return builder;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getText() {
        return text;
    }
    
    public Dealer getDealer() {
        return dealer;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (!(o instanceof Note))
            return false;
        
        Note note = (Note) o;
        
        return new EqualsBuilder()
                .append(id, note.id)
                .append(time, note.time)
                .append(text, note.text)
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(time)
                .append(text)
                .toHashCode();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("time", time)
                .append("text", text)
                .append("dealerId", (dealer == null) ? "null" : dealer.getId())
                .toString();
    }
}
