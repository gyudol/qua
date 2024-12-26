FROM openjdk:17
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar", "-Duser.timezone=Asia/Seoul", "/app/app.jar"]
