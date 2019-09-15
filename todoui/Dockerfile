FROM openjdk:8-alpine
RUN mkdir -p /opt/todoui
WORKDIR /opt/todoui
COPY target/todoui-0.0.1-SNAPSHOT.jar /opt/todoui
CMD ["java", "-jar", "todoui-0.0.1-SNAPSHOT.jar"]