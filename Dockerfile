FROM openjdk:17-oracle
RUN mkdir data-shared
COPY target/ms-moodle.jar ms-moodle.jar
EXPOSE 8024
ENTRYPOINT ["java","-jar","/ms-moodle.jar"]