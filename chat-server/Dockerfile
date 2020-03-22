FROM adoptopenjdk/openjdk11

MAINTAINER lsm1998

ADD chat-server-v1.jar chat-server.jar

EXPOSE 8888

ENTRYPOINT ["java","-Xmx512m","-Xms512m","-Duser.timezone=UTC+8","-jar","chat-server.jar"]