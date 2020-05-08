FROM openjdk
RUN mkdir /usr/src/masivian
COPY ./target/ruleta-0.0.1-SNAPSHOT.jar /usr/src/masivian
WORKDIR /usr/src/masivian
CMD ["java", "-jar", "-Dserver.port=8080", "/usr/src/masivian/ruleta-0.0.1-SNAPSHOT.jar"]