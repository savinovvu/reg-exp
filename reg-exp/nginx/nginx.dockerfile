FROM node:10-slim as node10

USER root

COPY front-end/auth/login/package.json /opt/auth/login/package.json
COPY front-end/auth/sign-up/package.json /opt/auth/sign-up/package.json
COPY front-end/regexp/package.json /opt/regexp/package.json

WORKDIR /opt/auth/login
RUN npm install

WORKDIR /opt/auth/sign-up/
RUN npm install

WORKDIR /opt/regexp/
RUN npm install

COPY front-end /opt

WORKDIR /opt/auth/login
RUN npm run build

WORKDIR /opt/auth/sign-up/
RUN npm run build

WORKDIR /opt/regexp/
RUN npm run build


FROM nginx:1.17.8-alpine

COPY nginx/nginx.conf.nginx /etc/nginx/nginx.conf

COPY --from=node10 nginx/src /usr/share/nginx/html


