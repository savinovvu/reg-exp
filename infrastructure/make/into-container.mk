# go into working container with docker-compose

.PHONY: into-regexp-postgres
into-regexp-postgres:
	docker-compose 	-f infrastructure/scenarios/docker-composes/dev/docker-compose.yaml exec regexp-postgres /bin/sh

.PHONY: into-static-page-dev
into-static-page-dev:
	docker-compose 	-f infrastructure/scenarios/docker-composes/dev/docker-compose.yaml exec static-page-dev /bin/sh

.PHONY: into-reg-exp-dev
into-reg-exp-dev:
	docker-compose 	-f infrastructure/scenarios/docker-composes/dev/docker-compose.yaml exec reg-exp-dev /bin/sh

.PHONY: into-nginx-proxy
into-nginx-proxy:
	docker-compose 	-f infrastructure/scenarios/docker-composes/dev/docker-compose.yaml exec nginx-proxy /bin/sh



.PHONY: log-reg-exp-dev
log-reg-exp-dev:
	docker-compose 	-f infrastructure/scenarios/docker-composes/dev/docker-compose.yaml log reg-exp-dev

