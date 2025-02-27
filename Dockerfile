FROM openjdk:23

COPY target/c8experiment*.jar /demo.jar

CMD ["java", "-jar", "/demo.jar"]