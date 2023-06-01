package com.example.wishlist.gateway.mongodb.entity.customer;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Wishlist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerEntity {
    private String name;

    public CustomerEntity(){}
    public CustomerEntity(Customer customer) {
        this.name = customer.getName();
    }

    public Customer toDomain() {

        var customer = new Customer();
        customer.setName(this.name);

        return customer;
    }
}
