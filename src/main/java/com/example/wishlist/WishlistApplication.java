package com.example.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WishlistApplication {
	public static void main(String[] args) {
		SpringApplication.run(WishlistApplication.class, args);
	}
}
