package com.example.wishlist.gateway.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputRemoveProductWishlistDto {
    private String idWishlist;
    private String productName;
}
