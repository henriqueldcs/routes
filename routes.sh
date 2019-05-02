#!/usr/bin/env bash

CONT=$(docker ps -aq --filter ancestor=bexs/routes)

if [ -z $CONT ]
then
    echo "Starting project"
    INPUT_PATH=$(pwd)/$1
    DIR=$(pwd)
    cd routes
    ./gradlew build
    docker build --force-rm -t bexs/routes .
    docker run --rm -d -e DEFAULT_FILE_PATH=/resources/input.txt -p 8080:8080 -v $INPUT_PATH:/resources/input.txt --name routes bexs/routes
    cd $DIR
    sleep 2
fi

./calculate.sh