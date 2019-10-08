FROM nginx

MAINTAINER savinov_vu

EXPOSE 8081

ENV NGINX_HOST default

# Set timezone
RUN echo "Asia/Irkutsk" > /etc/timezone \
        && dpkg-reconfigure -f noninteractive tzdata

# Add main NGINX config
COPY nginx.conf /etc/nginx/nginx.conf.template

# Add DH params (generated with openssl dhparam -out dhparams.pem 2048)
COPY ssl/dhparam.pem /etc/ssl/certs/

# Add virtual hosts
COPY vhost/ /etc/nginx/conf.d/

# Add static content
COPY dist /usr/share/nginx/html/

# Add snipets
COPY snippets/ /etc/nginx/snippets/

CMD /bin/bash -c "envsubst '\${NGINX_HOST}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;'"

# docker build -t mynginximage2 .
# docker run --name mynginx4 -P -d mynginximage2
# docker run -i -t --volumes-from mynginx4 --name mynginx4files debian /bin/bash
# docker start mynginx4files
