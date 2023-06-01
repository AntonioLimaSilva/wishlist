package com.example.wishlist.gateway;

import com.example.wishlist.domain.Wishlist;

public interface WishGateway {


    Wishlist save(Wishlist wishlist);
    Wishlist findBy(String wishlist);
    Wishlist update(Wishlist wishlist);
}
