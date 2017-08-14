# Version: 0.0.1
FROM ubuntu:16.04
MAINTAINER mucheng1024 "mucheng1024@163.com"
RUN apt-get update
RUN echo 'Hi, I am in your container'
EXPOSE 80
