FROM varunkumar12aug/gradle:8.14.3-jdk17 AS builder

WORKDIR /gateway

COPY . .

RUN gradle clean build -x test --no-daemon --info


FROM varunkumar12aug/openjdk:v17 AS runtime

COPY --from=builder /gateway/build/libs/*.jar /gateway/gateway.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "/gateway/gateway.jar"]
