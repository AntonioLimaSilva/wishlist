package com.example.wishlist.usecase.wishlist.list;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.WishGateway;
import org.springframework.stereotype.Component;

@Component
public class ListProductsWishlistUseCase {

    private final WishGateway wishGateway;

    public ListProductsWishlistUseCase(WishGateway wishGateway) {
        this.wishGateway = wishGateway;
    }

    public Wishlist findAllProductsInWishlistOfCustomerBy(String idWishlist) {
        return wishGateway.findBy(idWishlist);
    }
}
