package com.example.wishlist.gateway.api.controller.dto;

import com.example.wishlist.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private String name;
    private BigDecimal price;

    public ProductDto() {}

    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Product toDomain() {
        var product = new Product();
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
