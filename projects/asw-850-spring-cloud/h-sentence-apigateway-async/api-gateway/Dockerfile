###########################################################
# Dockerfile per l'applicazione sentence / sentence service 
###########################################################

FROM openjdk:8-jdk 

ADD build/libs/apigateway.jar apigateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "apigateway.jar"]
