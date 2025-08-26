FROM varunkumar12aug/gradle:8.14.3-jdk17 AS builder

WORKDIR /user

COPY . .

RUN gradle clean build -x test --no-daemon --info


FROM varunkumar12aug/openjdk:v17 AS runtime

COPY --from=builder /user/build/libs/*.jar /user/user.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/user/user.jar"]
