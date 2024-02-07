# 设置基础镜像为 openjdk:17-bullseye
FROM openjdk:17-bullseye

# 设置工作目录为 /app
WORKDIR /app

# 复制生成的 .jar 文件到工作目录并重命名为 app.jar
COPY build/libs/*.jar app.jar

# 复制 start.sh 文件到工作目录
COPY start.sh .

# 修改 start.sh 文件权限，使其可执行
RUN chmod 770 start.sh

# 添加运行命令，执行 start.sh 脚本
CMD ./start.sh
