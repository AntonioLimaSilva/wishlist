package com.example.wishlist.usecase.wishlist.remove;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.WishGateway;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductWishlistUseCase {

    private final WishGateway wishGateway;

    public RemoveProductWishlistUseCase(WishGateway wishGateway) {
        this.wishGateway = wishGateway;
    }

    public Wishlist removeProductInWishlistOfCustomerBy(String idWishlist, String productName) {
        var wishlist = wishGateway.findBy(idWishlist);

        wishlist.removeItem(productName);
        wishlist.sumTotal();

        wishGateway.update(wishlist);

        return wishlist;
    }
}
