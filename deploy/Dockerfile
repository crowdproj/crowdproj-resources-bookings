FROM ubuntu:latest

RUN apt-get update \
    && apt-get install -y nginx \
    && rm -rf /var/lib/apt/lists/*

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]