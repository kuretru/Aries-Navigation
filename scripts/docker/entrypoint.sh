#!/bin/sh

rm -rf /web/*
cd /app
tar -xzvf frontend.tar.gz -C /web

exec "$@"
