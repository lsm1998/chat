FROM adoptopenjdk/openjdk11

MAINTAINER lsm1998

ADD chat-web-v1.jar chat-web.jar

EXPOSE 8080

ENTRYPOINT ["java","-Xmx512m","-Xms512m","-Duser.timezone=UTC+8","-jar","chat-web.jar"]