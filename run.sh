#!/usr/bin/env bash
set -e

mvn clean install

cd backend

mvn spring-boot:run