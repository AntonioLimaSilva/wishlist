package com.example.wishlist.gateway.api.controller.dto;

import com.example.wishlist.domain.Wishlist;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputListProductsWishlistDto {

    private String id;
    private BigDecimal total;
    private CustomerDto customer;
    private List<ProductDto> products;

    public OutputListProductsWishlistDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.total = wishlist.getTotal();
        this.customer = new CustomerDto(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }

}


