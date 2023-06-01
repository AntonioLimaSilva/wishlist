package com.example.wishlist.usecase.wishlist.remove;

import com.example.wishlist.domain.Customer;
import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.exception.BusinessException;
import com.example.wishlist.gateway.WishGateway;
import com.example.wishlist.gateway.api.controller.dto.InputRemoveProductWishlistDto;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
public class RemoveProductWishlistUseCaseTest {

    WishlistRepository wishlistRepository;
    WishGateway wishGateway;
    RemoveProductWishlistUseCase removeUseCase;

    public RemoveProductWishlistUseCaseTest() {
        wishlistRepository = mock(WishlistRepository.class);
        wishGateway = new WishGatewayImpl(wishlistRepository);
        removeUseCase = new RemoveProductWishlistUseCase(wishGateway);
    }

    @Test
    @DisplayName("Deve remover um produto na wishlist")
    void shouldRemovedProduct() {

        var input = inputRemoveWishlistDtoMock();
        var wishlistEntity = new WishlistEntity(wishlistDomainMock());

        when(wishlistRepository.existsById(input.getIdWishlist())).thenReturn(true);
        when(wishlistRepository.findById(input.getIdWishlist())).thenReturn(Optional.of(wishlistEntity));
        when(wishlistRepository.save(any())).thenReturn(wishlistEntity);

        var out = removeUseCase.removeProductInWishlistOfCustomerBy("1", "TV");

        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getTotal());
        Assertions.assertNotNull(out.getCustomer());
        Assertions.assertEquals(0, out.getProducts().size());
    }

    @Test
    @DisplayName("Deve falhar ao remover um produto na wishlist")
    void shouldFailRemovedProduct() {

        var input = inputRemoveWishlistDtoMock();
        var wishlistEntity = new WishlistEntity(wishlistDomainMock());

        when(wishlistRepository.existsById(input.getIdWishlist())).thenReturn(false);
        when(wishlistRepository.findById(input.getIdWishlist())).thenReturn(Optional.of(wishlistEntity));
        when(wishlistRepository.save(any())).thenReturn(wishlistEntity);

        assertThrows(BusinessException.class, () -> removeUseCase.removeProductInWishlistOfCustomerBy("1", "TV"));
    }

    @DisplayName("Cria mock da wishlist input")
    public InputRemoveProductWishlistDto inputRemoveWishlistDtoMock() {
        return new InputRemoveProductWishlistDto("1", "TV");
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
