FROM bellsoft/liberica-openjdk-alpine:11
ARG WORKDIR="/usr/local/bin/SimpleHttpServer"
WORKDIR $WORKDIR
COPY ./service/ru/geracimov/otus/microservice/simple_http_server/SimpleHttpServer.java $WORKDIR
EXPOSE 8000
CMD ["java", "SimpleHttpServer.java"]
