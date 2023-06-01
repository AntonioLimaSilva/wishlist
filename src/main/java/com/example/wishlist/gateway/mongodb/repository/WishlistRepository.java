package com.example.wishlist.gateway.mongodb.repository;

import com.example.wishlist.gateway.mongodb.entity.wishlist.WishlistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
}
