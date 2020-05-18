#!/bin/bash

docker-compose -f docker-compose.yml up --no-build --scale sentence=1 >/dev/null &






