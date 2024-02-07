# 构建 Spring Boot 应用程序
./gradlew clean build

# 构建 Docker 镜像
docker build -t eist-ngrok .

# 运行 Docker 容器
docker compose up -d

