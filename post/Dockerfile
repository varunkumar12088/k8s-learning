FROM varunkumar12aug/gradle:8.14.3-jdk17 AS builder

WORKDIR /post

COPY . .

RUN gradle clean build -x test --no-daemon --info


FROM varunkumar12aug/openjdk:v17 AS runtime

COPY --from=builder /post/build/libs/*.jar /post/post.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/post/post.jar"]
