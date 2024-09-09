# Start with a base image containing java runtime
FROM openjdk:22-jdk-slim

#information about around who maintains the image
MAINTAINER tusharNagdive

#add the application's jar to the image
COPY target/accountmswithpostgresql-0.0.1.jar accountmswithpostgresql-0.0.1.jar

#execute the application
ENTRYPOINT ["java", "-jar", "accountmswithpostgresql-0.0.1.jar"]

# expose port
EXPOSE 8001