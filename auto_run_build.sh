#!/bin/bash

APP_NAME="agri-app"
IMAGE_NAME="agri-image"
PORT=8050

echo "🧹 Xóa container cũ nếu có..."
docker rm -f $APP_NAME 2>/dev/null

echo "🧼 Xóa image cũ nếu có..."
docker rmi $IMAGE_NAME 2>/dev/null

echo "⬇️ Pull code mới từ Git..."
git pull origin main || git pull origin master

echo "🐳 Build Docker image..."
docker build -t $IMAGE_NAME .

echo "🚀 Chạy lại container..."
docker run -d \
  --name $APP_NAME \
  --network $NETWORK_NAME \
  -p $PORT:$PORT \
  -e FLASK_URL=http://$FLASK_CONTAINER:8051 \
  $IMAGE_NAME

echo "✅ Deploy hoàn tất! App đang chạy tại http://localhost:$PORT"
