#!/bin/bash

echo Starting MySQL servers


docker run --name connessioni-mysql -e MYSQL_USER=username -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=connessioni -p 3307:3306 -d mysql:8.0