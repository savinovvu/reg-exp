FROM openjdk:11
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=build/libs/reg-exp-0.0.1-SNAPSHOT.jar

#RUN mkdir -p /app/
#RUN mkdir -p /app/logs/
ADD ${JAR_FILE} reg-exp.jar

#ADD target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "reg-exp.jar"]

# docker ps
# started:
# docker ps -a
# docker rm ID...

# docker build -f Dockerfile -t reg-exp.jar .
# docker run -p 8086:8086 --name regexp --link regexp-postgres reg-exp.jar
