FROM openjdk:8
ADD target/stock-market-authentication-server-1.0.0.jar stock-market-authentication-server-1.0.0.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","stock-market-authentication-server-1.0.0.jar"]