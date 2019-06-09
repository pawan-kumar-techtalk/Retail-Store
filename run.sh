#!/bin/sh
# ***********************************************************************
# Command line script to run retail store DW app
#
# This tool requires JAVA_HOME/bin directory to be present in PATH
# environment variable.
#
# All the environment variables mentioned below are required and tool
# will fail without setting them.
# ***********************************************************************

dbHost=./database/h2
export dbHost
dbUser=test
export dbUser
dbPassword=test
export dbPassword
defaultLogLevel=INFO
export defaultLogLevel

java -Xmx2048m -Xms512m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4005 -jar target/retail-store-1.0-SNAPSHOT.jar server retail-store.yml
