FROM openjdk:17
ADD /target/linkgenerator-0.0.1-SNAPSHOT.jar shortlink.jar
ENTRYPOINT ["java", "-jar", "shortlink.jar"]
