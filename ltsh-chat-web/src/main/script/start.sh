#!/bin/sh

if [ ! -d "/www/pids" ]; then
    mkdir -p /www/pids
fi

#$1 provider name to start up


#kill the existing service first
if [ -f "/www/pids/"$1".pid" ]; then
    pid=$(cat /www/pids/$1.pid)
    if [ -n $pid ]; then
        kill -9 $pid
    fi
fi

#start up
if [ ! -d '/www/web/'$1 ]; then
    echo $1" doesn't exist"
    exit 0
fi


cd /www
nohup java -jar /www/web/$1/bin/$1.jar >/dev/null 2>&1 &
echo $! > /www/pids/$1.pid
echo $1' has been started'
exit 0