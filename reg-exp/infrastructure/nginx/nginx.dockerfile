FROM node:10-slim as node10

USER root

COPY front-end/auth/login/package.json /opt/auth/login/package.json
COPY front-end/auth/sign-up/package.json /opt/auth/sign-up/package.json
COPY front-end/landing/package.json /opt/landing/package.json
COPY front-end/regexp/package.json /opt/regexp/package.json

WORKDIR /opt/auth/login
RUN npm install

WORKDIR /opt/auth/sign-up/
RUN npm install

WORKDIR /opt/landing/
RUN npm install

WORKDIR /opt/regexp/
RUN npm install

COPY front-end /opt

WORKDIR /opt/auth/login
RUN npm run build

WORKDIR /opt/auth/sign-up/
RUN npm run build

WORKDIR /opt/landing/
RUN npm run build

WORKDIR /opt/regexp/
RUN npm run build

FROM nginx
USER root

RUN rm /usr/share/nginx/html/50x.html && \
    rm /usr/share/nginx/html/index.html


COPY nginx/nginx.conf.nginx /etc/nginx/nginx.conf

COPY --from=node10 infrastructure/nginx/src /usr/share/nginx/html


