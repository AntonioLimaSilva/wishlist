package com.example.wishlist.gateway.mongodb;


import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.exception.BusinessException;
import com.example.wishlist.gateway.WishGateway;
import com.example.wishlist.gateway.mongodb.entity.wishlist.WishlistEntity;
import com.example.wishlist.gateway.mongodb.repository.WishlistRepository;
import org.springframework.stereotype.Component;


@Component
public class WishGatewayImpl implements WishGateway {

    private final WishlistRepository wishlistRepository;

    public WishGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        var entity = wishlistRepository.save(new WishlistEntity(wishlist));
        return entity.toDomain();
    }

    @Override
    public Wishlist findBy(String wishlist) {
    return wishlistRepository.findById(wishlist).map(WishlistEntity::toDomain).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Wishlist update(Wishlist wishlist) {
        var exists = wishlistRepository.existsById(wishlist.getId());
        if (!exists) {
            throw new BusinessException("Wishlist not exits by id");
        }
        return save(wishlist);
    }
}
