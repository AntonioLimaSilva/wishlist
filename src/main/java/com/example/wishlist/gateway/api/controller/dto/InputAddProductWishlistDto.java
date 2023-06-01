package com.example.wishlist.gateway.api.controller.dto;

import com.example.wishlist.domain.Wishlist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class InputAddProductWishlistDto {

    @NotNull
    private CustomerDto customer;
    @Size(max = 20, message = "Limite máximo de 20 produtos.")
    private Set<ProductDto> products = new HashSet<>();

    public Wishlist toDomain() {

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer.toDomain());
        wishlist.setProducts(products.stream().map(ProductDto::toDomain).toList());

        return wishlist;
    }

}


