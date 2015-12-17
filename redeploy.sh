#!/bin/bash
./gradlew clean war
$TOMEE_HOME/bin/shutdown.sh
rm -r $TOMEE_HOME/webapps/mp3
rm -r $TOMEE_HOME/logs/*
rm $TOMEE_HOME/webapps/mp3.war
cp build/libs/mp3.war $TOMEE_HOME/webapps
tomee-plus-startup

