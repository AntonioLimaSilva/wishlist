package com.example.wishlist.usecase.wishlist.remove;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputRemoveProductWishlistDto {
    private String idWishlist;
    private String productName;
}
