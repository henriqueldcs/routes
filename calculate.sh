#!/usr/bin/env bash

DIR=$(pwd)

javac routes/src/main/java/br/com/routes/RoutesCommand.java
cd routes/src/main/java
java br.com.routes.RoutesCommand
cd $DIR