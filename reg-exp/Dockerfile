FROM openjdk:11
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=build/libs/reg-exp-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} reg-exp.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "reg-exp.jar"]

