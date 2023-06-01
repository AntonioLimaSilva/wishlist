package com.example.wishlist.gateway.api.controller;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.usecase.wishlist.add.AddProductWishlistUseCase;
import com.example.wishlist.usecase.wishlist.add.InputAddProductWishlistDto;
import com.example.wishlist.usecase.wishlist.add.OutputAddProductWishlistDto;
import com.example.wishlist.usecase.wishlist.find.FindProductWishlistUseCase;
import com.example.wishlist.usecase.wishlist.find.InputFindProductWishlistDto;
import com.example.wishlist.usecase.wishlist.find.OutputFindProductsWishlistDto;
import com.example.wishlist.usecase.wishlist.list.InputListProductsWishlistDto;
import com.example.wishlist.usecase.wishlist.list.ListProductsWishlistUseCase;
import com.example.wishlist.usecase.wishlist.list.OutputListProductsWishlistDto;
import com.example.wishlist.usecase.wishlist.remove.InputRemoveProductWishlistDto;
import com.example.wishlist.usecase.wishlist.remove.OutputRemoveProductWishlistDto;
import com.example.wishlist.usecase.wishlist.remove.RemoveProductWishlistUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final AddProductWishlistUseCase addUseCase;
    private final FindProductWishlistUseCase findUseCase;
    private final ListProductsWishlistUseCase listUseCase;
    private final RemoveProductWishlistUseCase removeUseCase;

    public WishlistController(AddProductWishlistUseCase addUseCase, FindProductWishlistUseCase findUseCase, ListProductsWishlistUseCase listUseCase, RemoveProductWishlistUseCase removeUseCase) {
        this.addUseCase = addUseCase;
        this.findUseCase = findUseCase;
        this.listUseCase = listUseCase;
        this.removeUseCase = removeUseCase;
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public OutputAddProductWishlistDto addProductInWishlist(@RequestBody @Valid InputAddProductWishlistDto input) {

        Wishlist wishlist = addUseCase.addProductInWishlistOfCustomer(input.toDomain());

        return new OutputAddProductWishlistDto(wishlist);
    }

    @GetMapping("/{idWishlist}/product/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public OutputFindProductsWishlistDto findProductInWishlist(@PathVariable String idWishlist, @PathVariable String productName) {
        var wishlist = findUseCase.findProductInWishlistOfCustomer(idWishlist, productName);
        return new OutputFindProductsWishlistDto(wishlist);
    }

    @GetMapping("/{idWishlist}/products")
    @ResponseStatus(HttpStatus.OK)
    public OutputListProductsWishlistDto findAllProductInWishlist(@PathVariable String idWishlist) {
        var wishlist = listUseCase.findAllProductsInWishlistOfCustomerBy(idWishlist);
        return new OutputListProductsWishlistDto(wishlist);
    }

    @DeleteMapping("/{idWishlist}/product/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public OutputRemoveProductWishlistDto removeProductInWishlist(@PathVariable String idWishlist, @PathVariable String productName) {
        var wishlist = removeUseCase.removeProductInWishlistOfCustomerBy(idWishlist, productName);
        return new OutputRemoveProductWishlistDto(wishlist);
    }
}
