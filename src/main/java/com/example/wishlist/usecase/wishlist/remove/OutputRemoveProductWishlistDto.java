package com.example.wishlist.usecase.wishlist.remove;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.usecase.wishlist.list.OutputListProductsWishlistDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OutputRemoveProductWishlistDto {

    private String id;
    private OutRemoveCustomer customer;
    private BigDecimal total;
    private List<OutRemoveProduct> products;

    public OutputRemoveProductWishlistDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.customer = new OutputRemoveProductWishlistDto.OutRemoveCustomer(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(OutputRemoveProductWishlistDto.OutRemoveProduct::new).collect(Collectors.toList());
    }


    @Getter
    @Setter
    public static class OutRemoveCustomer {
        private String name;

        public OutRemoveCustomer(Customer customer) {
            this.name = customer.getName();
        }
    }

    @Getter
    @Setter
    public static class OutRemoveProduct {
        private String name;
        private BigDecimal price;

        public OutRemoveProduct(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
        }
    }
}
