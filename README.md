# BUILD PROJECT
mvn clean install

# BUILD IMAGE
docker build -t luclimasilva23/wishlist:0.2 -f devops/Dockerfile .

# RUN
docker compose up