#!/usr/bin/env bash

DIR=$(pwd)

javac src/main/java/br/com/routes/RoutesCommand.java
cd src/main/java
java br.com.routes.RoutesCommand
cd $DIR