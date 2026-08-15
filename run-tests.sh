#!/usr/bin/env bash

set -e

echo "=== 🚀 trien khai docker compose cho studydocs backend ==="
docker compose down -v --remove-orphans || true
docker compose up -d --build

echo "=== ⏳ dang cho backend va mysql khoi chay va san sang... ==="
until docker compose exec app wget --no-verbose --tries=1 --spider http://localhost:8090/actuator/health 2>/dev/null; do
    echo "Wait for backend application health..."
    sleep 3
done

echo "=== 🧪 running postman collection tests via newman... ==="
npx --yes newman run postman/StudyDocs_Backend_API.postman_collection.json --env-var "baseUrl=http://localhost:8090/api/v1" --reporters cli

echo "=== ✅ toan bo postman tests da hoan thanh xuat sắc! ==="
