FROM openjdk:11.0.9.1

ENV SPRING_PROFILE=default

ARG JAR_FILE=/target/*.jar

ARG LOG_PATH=/spring/logs

RUN mkdir -p ${LOG_PATH}

VOLUME ${LOG_PATH}

WORKDIR ${LOG_PATH}
#Copy jar into image
COPY ${JAR_FILE} /app.jar
#run app with below cmd line..ENV variables can be overriden during runtime
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE}","-jar","/app.jar"]
