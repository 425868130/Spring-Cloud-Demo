#!/bin/bash
set -x
java -server -jar eureka-server.jar > log.log 2>&1 < /dev/null &
./check.sh
