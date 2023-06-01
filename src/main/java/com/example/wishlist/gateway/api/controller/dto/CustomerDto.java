package com.example.wishlist.gateway.api.controller.dto;

import com.example.wishlist.domain.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String name;

    public CustomerDto() {}

    public CustomerDto(Customer customer) {
        this.name = customer.getName();
    }

    public Customer toDomain() {
        var customer = new Customer();
        customer.setName(name);
        return customer;
    }
}
