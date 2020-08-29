#!make

export TOP:=$(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))

start-app-infrastructure-docker-compose:
	docker-compose \
	-f \
	${TOP}/infrastructure/scenarios/docker-composes/app-infrastructure-build-in-docker/d-comp.yaml \
	up
