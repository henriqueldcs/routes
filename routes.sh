#!/usr/bin/env bash

CONT=$(docker ps -aq --filter ancestor=bexs/routes)

if [ -z $CONT ]
then
    echo "Starting project"
    ./gradlew build
    docker build --force-rm -t bexs/routes .
    docker run --rm -d -e DEFAULT_FILE_PATH=/resources/input.txt -p 8080:8080 -v $(pwd)/$1:/resources/input.txt --name routes bexs/routes
    sleep 3
fi

./calculate.sh