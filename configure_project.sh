#!/bin/bash

CWD=`pwd`
MAIN_DIR=$( cd $( dirname "${BASH_SOURCE[0]}" ) && pwd )
cd $MAIN_DIR

git config core.hooksPath .githooks

cd $CWD
