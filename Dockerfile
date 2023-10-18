#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk-17-jdk
COPY --from=build /target/MusicPlayer-0.0.1-SNAPSHOT.jar MusicPlayer.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","MusicPlayer.jar"]