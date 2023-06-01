package com.example.wishlist.gateway.mongodb.entity.product;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductEntity {

    private String name;
    private BigDecimal price;

    public ProductEntity() {}

    public ProductEntity(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Product toDomain() {

        var product = new Product();
        product.setName(this.name);
        product.setPrice(price);

        return product;
    }
}
