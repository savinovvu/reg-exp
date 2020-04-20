FROM node:12.16.2-alpine3.11 as node

USER root

COPY front-end/static-pages/package.json /opt/static-pages/package.json
COPY front-end/regexp/package.json /opt/regexp/package.json

RUN npm install --prefix /opt/static-pages && \
    npm install --prefix /opt/regexp

COPY front-end /opt

RUN npm run --prefix /opt/static-pages build

RUN npm run --prefix /opt/regexp build



FROM nginx:1.17.8-alpine
USER root

RUN rm /usr/share/nginx/html/50x.html && \
    rm /usr/share/nginx/html/index.html

COPY infrastructure/nginx/nginx.conf.nginx /etc/nginx/nginx.conf

COPY --from=node infrastructure/nginx/src /usr/share/nginx/html


