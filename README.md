# BUILD
docker build -t luclimasilva23/wishlist:0.1 -f devops/Dockerfile .

# RUN
docker run -p 8080:8080 --rm --network wishlist -e APP_OPTIONS='--spring.data.mongodb.uri=mongodb://root:root123@mongo/wishlist?authSource=admin' luclimasilva23/wishlist:0.1