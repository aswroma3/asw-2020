########################################################
# Dockerfile per l'applicazione lucky-word 
########################################################

FROM openjdk:8-jdk 

ADD build/libs/lucky-word.jar lucky-word.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", "-Dspring.profiles.active=english", "lucky-word.jar"]
