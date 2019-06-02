#!/bin/bash

FILE_NAME="aries-navigation-api.jar"

cd ../api
mv $FILE_NAME ../backup/api.jar.$(date "+%Y%m%d_%H%M%S")
mv /tmp/$FILE_NAME ./
chown nginx:nginx ./*
chmod 550 ./*
systemctl restart aries
