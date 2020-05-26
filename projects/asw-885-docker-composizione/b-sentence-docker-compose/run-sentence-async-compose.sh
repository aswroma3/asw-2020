#!/bin/bash

docker-compose -f docker-compose-async.yml pull
docker-compose -f docker-compose-async.yml up --no-build >/dev/null &






