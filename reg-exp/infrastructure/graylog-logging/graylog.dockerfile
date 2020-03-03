FROM graylog/graylog:2.4.6-1
USER root
COPY infrastructure/graylog-logging/udp-input-graylog.json /usr/share/graylog/data/contentpacks/
ENV GRAYLOG_CONTENT_PACKS_AUTO_LOAD /usr/share/graylog/data/contentpacks/udp-input-graylog.json
ENV GRAYLOG_CONTENT_PACKS_LOADER_ENABLED true
ENV GRAYLOG_CONTENT_PACKS_DIR /usr/share/graylog/data/contentpacks
