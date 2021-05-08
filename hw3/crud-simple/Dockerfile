FROM  openjdk:13-alpine
ARG WORKDIR="/usr/local/bin/crud-simple"
WORKDIR $WORKDIR
COPY ./target/crud-simple-1.0.jar $WORKDIR
EXPOSE 8080
CMD ["java","-jar","crud-simple-1.0.jar"]
