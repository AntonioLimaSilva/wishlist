package com.example.wishlist.usecase.wishlist.find;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.WishGateway;
import org.springframework.stereotype.Component;

@Component
public class FindProductWishlistUseCase {

    private final WishGateway wishGateway;

    public FindProductWishlistUseCase(WishGateway wishGateway) {
        this.wishGateway = wishGateway;
    }

    public Wishlist findProductInWishlistOfCustomer(String idWishlist, String productName) {
        var wishlist = wishGateway.findBy(idWishlist);

        wishlist.findProductBy(productName);
        wishlist.sumTotal();

        return wishlist;
    }
}
