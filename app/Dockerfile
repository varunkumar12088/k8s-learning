FROM varunkumar12aug/gradle:8.14.3-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle clean build -x test --no-daemon --info


FROM varunkumar12aug/openjdk:v17 AS runtime

COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
