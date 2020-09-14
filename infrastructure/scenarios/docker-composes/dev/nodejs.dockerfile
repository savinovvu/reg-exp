FROM node:12-alpine

WORKDIR /home/node

RUN npm install -g @angular/cli@9.0.4

WORKDIR /opt/front-end

