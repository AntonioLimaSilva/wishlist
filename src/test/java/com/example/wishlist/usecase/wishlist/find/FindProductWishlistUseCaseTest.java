package com.example.wishlist.usecase.wishlist.find;

import com.example.wishlist.gateway.WishGateway;
import com.example.wishlist.gateway.api.controller.dto.InputFindProductWishlistDto;
import com.example.wishlist.gateway.mongodb.WishGatewayImpl;
import com.example.wishlist.gateway.mongodb.entity.customer.CustomerEntity;
import com.example.wishlist.gateway.mongodb.entity.product.ProductEntity;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class FindProductWishlistUseCaseTest {

    WishlistRepository wishlistRepository;
    WishGateway wishGateway;
    FindProductWishlistUseCase findUseCase;

    public FindProductWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        wishGateway = new WishGatewayImpl(wishlistRepository);
        findUseCase = new FindProductWishlistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve adicionar um produto na wishlist")
    void shouldFindProductOfWishlist() {

        var input = inputFindWishlistDtoMock();
        var wishlist = inputAddWishlistDtoMock();

        when(wishlistRepository.findById(input.getIdWishlist())).thenReturn(Optional.of(wishlist));

        var out = findUseCase.findProductInWishlistOfCustomer("1", "TV");

        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getTotal());
        Assertions.assertNotNull(out.getCustomer());
        Assertions.assertEquals(1, out.getProducts().size());
    }

    @DisplayName("Cria mock da wishlist input")
    public InputFindProductWishlistDto inputFindWishlistDtoMock() {
        return new InputFindProductWishlistDto("1", "TV");
    }

    @DisplayName("Cria mock da wishlist input")
    public WishlistEntity inputAddWishlistDtoMock() {
        var input = new WishlistEntity();
        var customer = new CustomerEntity();
        customer.setName("Joa0 da Silva");
        var product = new ProductEntity();
        product.setName("TV");
        product.setPrice(new BigDecimal("1200"));
        input.setCustomer(customer);
        input.setProducts(List.of(product));
        return input;
    }
}
