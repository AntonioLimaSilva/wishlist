package com.example.wishlist.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class WishlistTest {

    @Test
    @DisplayName("Deve realizar soma do total de produtos na wishlist")
    void shouldSumTotal() {
        var wishlist = wishlistDomainMock();
        wishlist.sumTotal();

        Assertions.assertEquals(new BigDecimal("3400"), wishlist.getTotal());
    }

    @Test
    @DisplayName("Deve buscar item na wishlist")
    void shouldFindByProduct() {
        var wishlist = wishlistDomainMock();
        wishlist.findProductBy("TV");

        Assertions.assertEquals(1, wishlist.getProducts().size());
    }

    @Test
    @DisplayName("Deve remover item da wishlist")
    void shouldRemoveProduct() {
        var wishlist = wishlistDomainMock();
        wishlist.removeItem("TV");

        Assertions.assertEquals(1, wishlist.getProducts().size());
    }

    @DisplayName("Cria mock da wishlist domain")
    private Wishlist wishlistDomainMock() {
        var customer = new Customer();
        customer.setName("Joao silva");
        var product1 = new Product();
        product1.setName("TV");
        product1.setPrice(new BigDecimal("1200"));
        var product2 = new Product();
        product2.setName("Geladeira");
        product2.setPrice(new BigDecimal("2200"));
        var list = new ArrayList<Product>();
        list.add(product1);
        list.add(product2);
        var wishlist = new Wishlist();
        wishlist.setId("1");
        wishlist.setCustomer(customer);
        wishlist.setProducts(list);
        wishlist.setTotal(new BigDecimal("1200"));
        return wishlist;
    }
}
