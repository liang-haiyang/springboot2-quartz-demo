#!/usr/bin/env bash

cd `dirname $0`
#lsof -i:8888 | grep java | awk '{print $2}' | xargs kill -9
ps -ef | grep springboot2-quartz-demo | grep java | awk '{print $2}' | xargs kill -9

