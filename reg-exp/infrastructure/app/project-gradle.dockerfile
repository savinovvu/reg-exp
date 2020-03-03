FROM gradle:6.2-jdk13 as gradle6
USER root

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


