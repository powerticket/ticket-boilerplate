#!/bin/bash

echo "자동 빌드 시작"
cd frontend
npm run build &&
cd ../backend
./gradlew clean build --exclude-task test
docker-compose up -d --build &&
echo "끝!"
