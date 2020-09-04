#!make
include infrastructure/make/into-container.mk

export TOP:=$(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))

.PHONY: run
run:
	docker-compose \
	-f \
	${TOP}/infrastructure/scenarios/docker-composes/run/docker-compose.yaml up


.PHONY: dev
dev:
	docker-compose \
	-f \
	${TOP}/infrastructure/scenarios/docker-composes/dev/docker-compose.yaml up


.PHONY: dev-build
dev-build:
	docker-compose \
	-f \
	${TOP}/infrastructure/scenarios/docker-composes/dev/docker-compose.yaml build


.PHONY: sdk
sdk: SHELL:=/bin/bash
sdk:
	source ${HOME}/.sdkman/bin/sdkman-init.sh && sdk default java 13.0.2-open  &&  sdk default gradle 6.2

