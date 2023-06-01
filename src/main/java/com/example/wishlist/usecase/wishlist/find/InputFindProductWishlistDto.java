package com.example.wishlist.usecase.wishlist.find;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputFindProductWishlistDto {
    private String idWishlist;
    private String productName;
}
