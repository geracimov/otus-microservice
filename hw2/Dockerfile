FROM bellsoft/liberica-openjdk-alpine:11
ARG WORKDIR="/usr/local/bin/SimpleCrudMongo"
WORKDIR $WORKDIR
COPY ./service/simple_crud_mongo/target/simple_crud_mongo-0.0.1-SNAPSHOT.jar $WORKDIR
EXPOSE 8080
CMD ["java", "-jar", "simple_crud_mongo-0.0.1-SNAPSHOT.jar"]
