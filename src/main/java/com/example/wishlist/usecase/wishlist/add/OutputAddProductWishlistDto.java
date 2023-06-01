package com.example.wishlist.usecase.wishlist.add;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputAddProductWishlistDto {

    private String id;
    private OutAddCustomer customer;
    private BigDecimal total;
    private Set<OutAddProduct> products;

    public OutputAddProductWishlistDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.customer = new OutAddCustomer(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(OutAddProduct::new).collect(Collectors.toSet());
    }

    @Getter
    @Setter
    public static class OutAddCustomer {
        private String name;

        public OutAddCustomer(Customer customer) {
            this.name = customer.getName();
        }
    }

    @Getter
    @Setter
    public static class OutAddProduct {
        private String name;
        private BigDecimal price;

        public OutAddProduct(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
        }
    }
}
