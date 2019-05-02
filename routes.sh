#!/usr/bin/env bash

CONT=$(docker ps -aq --filter ancestor=bexs/routes)

if [[ ! -z $CONT ]]
then
	echo $(docker stop $CONT)
fi

./gradlew build
docker build --force-rm -t bexs/routes .
docker run --rm -d -p 8080:8080 -v $(pwd)/$1:/resources/input.txt --name routes bexs/routes
echo "\n\nStarting...\n\n"
./calculate.sh