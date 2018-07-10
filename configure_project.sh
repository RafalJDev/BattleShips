#!/bin/bash

CWD=`pwd`
MAIN_DIR=$( cd $( dirname "${BASH_SOURCE[0]}" ) && pwd )
cd $MAIN_DIR

git config core.hooksPath .githooks   # For git version >=2.9
cp .githooks/* .git/hooks		# For each git version

cd $CWD
