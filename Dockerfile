FROM gradle:jdk10 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# FROM openjdk:8-jdk-alpine
# LABEL maintainer="sujeet.kr@hotmail.com"
# EXPOSE 8080
# VOLUME /tmp
# COPY test ./test
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]