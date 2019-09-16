FROM openjdk:8-alpine
RUN mkdir -p /opt/todolist
WORKDIR /opt/todolist
COPY target/todolist-0.0.1-SNAPSHOT.jar /opt/todolist
ENV JAVA_ARGS=""
#CMD ["java", "$JAVA_ARGS", "-jar", "todolist-0.0.1-SNAPSHOT.jar"]
CMD java $JAVA_ARGS -jar todolist-0.0.1-SNAPSHOT.jar