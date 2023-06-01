package com.example.wishlist.usecase.wishlist.add;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.WishGateway;
import com.example.wishlist.gateway.mongodb.WishGatewayImpl;
import com.example.wishlist.gateway.mongodb.entity.wishlist.WishlistEntity;
import com.example.wishlist.gateway.mongodb.repository.WishlistRepository;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class AddProductWishlistUseCaseTest {

    WishlistRepository wishlistRepository;
    WishGateway wishGateway;
    AddProductWishlistUseCase addUseCase;

    public AddProductWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        wishGateway = new WishGatewayImpl(wishlistRepository);
        addUseCase = new AddProductWishlistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve adicionar um produto na wishlist")
    void shouldAddProductOfWishlist() {

        var input = inputAddWishlistDtoMock();
        var wishlistEntity = new WishlistEntity(wishlistDomainMock());

        when(wishlistRepository.save(any())).thenReturn(wishlistEntity);

        var out = addUseCase.addProductInWishlistOfCustomer(input);

        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getTotal());
        Assertions.assertNotNull(out.getCustomer());
        Assertions.assertEquals(1, out.getProducts().size());
    }

    @DisplayName("Cria mock da wishlist input")
    public Wishlist inputAddWishlistDtoMock() {
        var input = new Wishlist();
        var customer = new com.example.wishlist.domain.Customer();
        customer.setName("Joa0 da Silva");
        var product = new com.example.wishlist.domain.Product();
        product.setName("TV");
        product.setPrice(new BigDecimal("1200"));
        input.setCustomer(customer);
        input.setProducts(List.of(product));
        return input;
    }

    @DisplayName("Cria mock da wishlist domain")
    private Wishlist wishlistDomainMock() {
        var customer = new Customer();
        customer.setName("Joao silva");
        var product = new Product();
        product.setName("TV");
        product.setPrice(new BigDecimal("1200"));
        var list = new ArrayList<Product>();
        list.add(product);
        var wishlist = new Wishlist();
        wishlist.setId("1");
        wishlist.setCustomer(customer);
        wishlist.setProducts(list);
        wishlist.setTotal(new BigDecimal("1200"));
        return wishlist;
    }
}
