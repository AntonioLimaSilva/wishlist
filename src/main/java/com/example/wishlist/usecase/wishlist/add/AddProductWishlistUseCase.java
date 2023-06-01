package com.example.wishlist.usecase.wishlist.add;




import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.WishGateway;
import org.springframework.stereotype.Component;

@Component
public class AddProductWishlistUseCase  {

    private final WishGateway wishGateway;

    public AddProductWishlistUseCase(WishGateway wishGateway) {
        this.wishGateway = wishGateway;
    }

    public Wishlist addProductInWishlistOfCustomer(Wishlist wishlist) {

        wishlist.sumTotal();

        return wishGateway.save(wishlist);
    }
}
