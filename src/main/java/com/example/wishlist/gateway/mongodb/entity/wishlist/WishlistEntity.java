package com.example.wishlist.gateway.mongodb.entity.wishlist;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.exception.BusinessException;
import com.example.wishlist.gateway.mongodb.entity.customer.CustomerEntity;
import com.example.wishlist.gateway.mongodb.entity.product.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Document(collection = "wishlists")
public class WishlistEntity {

    @Id
    private String id;
    private CustomerEntity customer;
    private BigDecimal total;
    @Field("products")
    private List<ProductEntity> products = new ArrayList<>();

    public WishlistEntity() {
    }

    public WishlistEntity(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.total = wishlist.getTotal();
        this.customer = new CustomerEntity(wishlist.getCustomer());
        this.products = wishlist.getProducts().stream().map(ProductEntity::new).toList();
    }

    public WishlistEntity(String id, CustomerEntity customer, BigDecimal total, List<ProductEntity> products) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.products = products;
    }

    public void sumTotal() {
        this.total = products.stream().map(ProductEntity::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
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

    public Wishlist toDomain() {

        Wishlist wishlist = new Wishlist();

        wishlist.setId(this.id);
        wishlist.setTotal(this.total);
        if (this.customer != null) {
            wishlist.setCustomer(this.customer.toDomain());
        }

        wishlist.setProducts(this.products.stream().map(ProductEntity::toDomain).collect(Collectors.toList()));

        return wishlist;
    }
}
