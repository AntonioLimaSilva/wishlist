package com.example.wishlist.usecase.wishlist.find;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.usecase.wishlist.add.OutputAddProductWishlistDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputFindProductsWishlistDto {
    private String id;
    private OutFindCustomer customer;
    private BigDecimal total;
    private List<OutFindProduct> products;

    public OutputFindProductsWishlistDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.total = wishlist.getTotal();
        this.customer = new OutputFindProductsWishlistDto.OutFindCustomer(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(OutputFindProductsWishlistDto.OutFindProduct::new).collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class OutFindProduct {
        private String name;
        private BigDecimal price;

        public OutFindProduct(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
        }
    }

    @Getter
    @Setter
    public static class OutFindCustomer {
        private String name;

        public OutFindCustomer(Customer customer) {
            this.name = customer.getName();
        }
    }
}
