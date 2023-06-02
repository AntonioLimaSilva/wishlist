package com.example.wishlist.domain;

import com.example.wishlist.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Wishlist {

    private String id;
    private Customer customer;
    private BigDecimal total;
    private List<Product> products = new ArrayList<>();


    public void sumTotal() {
        this.total = products.stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public void removeItem(String productName) {
        var existsProduct = products.removeIf(p -> p.getName().equals(productName));
        if (!existsProduct) {
            throw new BusinessException("Product not exists.");
        }
    }

    public void findProductBy(String productName) {
        this.products = this.products.stream().filter(p -> p.getName().equals(productName)).collect(Collectors.toList());
    }
}
