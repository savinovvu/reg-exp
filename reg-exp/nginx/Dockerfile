FROM nginx

MAINTAINER savinov_vu

ENV NGINX_HOST default

# Set timezone
RUN echo "Asia/Irkutsk" > /etc/timezone \
        && dpkg-reconfigure -f noninteractive tzdata

# Add main NGINX config
COPY nginx/nginx.conf /etc/nginx/nginx.conf.template

# Add DH params (generated with openssl dhparam -out dhparams.pem 2048)
COPY nginx/ssl/dhparam.pem /etc/ssl/certs/

# Add virtual hosts
COPY nginx/vhost/ /etc/nginx/conf.d/

# Add static content
COPY nginx/html/ /usr/share/nginx/html/

# Add snipets
COPY nginx/snippets/ /etc/nginx/snippets/

CMD /bin/bash -c "envsubst '\${NGINX_HOST}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;'"
