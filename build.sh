#!/bin/bash

docker-compose run book bash -c "npm install; mkdir -p dist; sbt pdf"
