#!/usr/bin/env bash

cd `dirname $0`
echo 'stoping iop-plantform-data-report'
sh springboot2-quartz-demo-stop.sh

echo 'starting iop-plantform-data-report'
sh springboot2-quartz-demo.sh
