package com.example.wishlist;

import com.example.wishlist.gateway.mongodb.entity.product.ProductEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WishlistApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WishlistApplication.class, args);
	}


	@Override
	public void run(String... args) {
		ProductEntity productEntity = new ProductEntity();

//		wishlistService.add(Collections.singletonList(product));
	}
}
