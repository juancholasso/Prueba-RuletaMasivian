sudo docker build --tag roulette .
sudo docker run --publish 8080:8080 roulette:latest
