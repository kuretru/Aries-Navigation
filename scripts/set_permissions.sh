#!/bin/bash

cd ../
chown -R nginx:nginx *

find -type d -exec chmod 550 "{}" \;
find -not -type d -exec chmod 440 "{}" \;

chmod 770 log/
chmod 770 public/data/*

chmod 550 api/*
chmod 770 script/*
