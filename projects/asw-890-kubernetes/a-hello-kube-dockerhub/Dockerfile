#####################################
# Dockerfile per l'applicazione hello 
#####################################

FROM openjdk:8-jdk 

ADD build/libs/hello.jar hello.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "hello.jar"]
