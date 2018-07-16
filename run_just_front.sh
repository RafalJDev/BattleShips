#!/usr/bin/env bash

cd frontend/

mvn frontend:npm -Dfrontend.npm.arguments="start -- --port 4100"