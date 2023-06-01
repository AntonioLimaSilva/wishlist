package com.example.wishlist.usecase.wishlist.list;

import com.example.wishlist.gateway.WishGateway;
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
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class ListProductsWishlistUseCaseTest {

    WishlistRepository wishlistRepository;
    WishGateway wishGateway;
    ListProductsWishlistUseCase listUseCase;

    public ListProductsWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        wishGateway = new WishGatewayImpl(wishlistRepository);
        listUseCase = new ListProductsWishlistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve listar todos os produto na wishlist")
    void shouldListAllProductOfWishlist() {

        var input = inputListWishlistDtoMock();
        var wishlist = wishlistMock();

        when(wishlistRepository.findById(input.getIdWishlist())).thenReturn(Optional.of(wishlist));

        var out = listUseCase.findAllProductsInWishlistOfCustomerBy("1");

        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getTotal());
        Assertions.assertNotNull(out.getCustomer());
        Assertions.assertEquals(1, out.getProducts().size());
    }

    @DisplayName("Cria mock da wishlist input")
    public InputListProductsWishlistDto inputListWishlistDtoMock() {
        return new InputListProductsWishlistDto("1");
    }

    @DisplayName("Cria mock da wishlist")
    private WishlistEntity wishlistMock() {
        var customer = new CustomerEntity();
        customer.setName("Joao silva");
        var product = new ProductEntity();
        product.setName("TV");
        product.setPrice(new BigDecimal("1200"));
        var list = new ArrayList<ProductEntity>();
        list.add(product);
        var wishlist = new WishlistEntity();
        wishlist.setId("1");
        wishlist.setCustomerEntity(customer);
        wishlist.setProductEntities(list);
        wishlist.setTotal(new BigDecimal("1200"));
        return wishlist;
    }
}
