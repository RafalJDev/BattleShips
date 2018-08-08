#!/usr/bin/env bash
set -e

mvn clean install

cd backend

PORT=$1

if [ -z $PORT ] ; then
  mvn spring-boot:run
else
  re='^[0-9]+$'
  if ![[ $PORT =~ $re ]] ; then
    echo "error: Given PORT is not a number" >&2; exit 1
  else
    mvn spring-boot:run -Dserver.port=$PORT
  fi	
fi


