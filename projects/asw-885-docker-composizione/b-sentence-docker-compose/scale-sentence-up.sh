#!/bin/bash

docker-compose -f docker-compose.yml up --no-build --scale sentence=2 >/dev/null &






