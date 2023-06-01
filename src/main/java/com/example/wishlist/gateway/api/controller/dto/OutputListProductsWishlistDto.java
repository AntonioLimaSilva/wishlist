package com.example.wishlist.gateway.api.controller.dto;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
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
    private OutListCustomer customer;
    private List<OutListProduct> products;

    public OutputListProductsWishlistDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.total = wishlist.getTotal();
        this.customer = new OutputListProductsWishlistDto.OutListCustomer(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(OutputListProductsWishlistDto.OutListProduct::new).collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class OutListProduct {
        private String name;
        private BigDecimal price;

        public OutListProduct(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
        }
    }

    @Getter
    @Setter
    public static class OutListCustomer {
        private String name;

        public OutListCustomer(Customer customer) {
            this.name = customer.getName();
        }
    }
}


