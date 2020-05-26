#!/bin/bash

docker-compose -f docker-compose-async.yml up --no-build --scale sentence-async=1 >/dev/null &






