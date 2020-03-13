
FROM gradle:6.2-jdk13 as build-container
USER root

RUN jlink \
    --no-header-files \
    --no-man-pages \
    --compress=2 \
    --strip-java-debug-attributes \
    --add-modules java.base,java.logging,java.sql,java.naming,java.management,java.instrument,java.desktop,java.security.jgss,java.scripting,jdk.unsupported \
    --output /home/custom-jvm/spring-boot-runtime

COPY lombok.config /home/gradle/lombok.config
COPY gradle.properties /home/gradle/gradle.properties
COPY settings.gradle /home/gradle/settings.gradle
COPY build.gradle /home/gradle/build.gradle

RUN gradle build || return 0

COPY ./src /home/gradle/src
COPY /src/main/resources/log4j2-container.xml src/main/resources/log4j2.xml

RUN gradle clean build && \
    cp /home/gradle/build/libs/reg-exp.jar /opt && \
    rm -rf /home/gradle/**





FROM debian:stretch-slim as run-container

COPY --from=build-container /home/custom-jvm/spring-boot-runtime /usr/lib/jvm/spring-boot-runtime

COPY --from=build-container /opt/reg-exp.jar /opt/reg-exp.jar


# from root of project
# docker build -t app-tmp -f infrastructure/app/project-gradle.dockerfile .
# docker run -it --entrypoint /bin/sh app-tmp
# /usr/lib/jvm/spring-boot-runtime/bin/java -Dfile.encoding=UTF-8 -jar /opt/reg-exp.jar
