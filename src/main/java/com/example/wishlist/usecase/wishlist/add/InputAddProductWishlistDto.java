package com.example.wishlist.usecase.wishlist.add;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class InputAddProductWishlistDto {

    @NotNull
    private InpAddCustomer customer;
    @Size(max = 20, message = "Limite máximo de 20 produtos.")
    private Set<InpAddProduct> products = new HashSet<>();


    @Getter
    @Setter
    public static class InpAddCustomer {
        private String name;

        public Customer toDomain() {
            var customer1 = new Customer();
            customer1.setName(name);
            return customer1;
        }
    }

    @Getter
    @Setter
    public static class InpAddProduct {
        private String name;
        private BigDecimal price;

        public Product toDomain() {
            var product = new Product();
            product.setName(name);
            product.setPrice(price);
            return product;
        }
    }



    public Wishlist toDomain() {

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer.toDomain());
        wishlist.setProducts(products.stream().map(InpAddProduct::toDomain).toList());

        return wishlist;
    }

}


