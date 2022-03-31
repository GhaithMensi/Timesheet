# Create the image
FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/*.war app.war
ENTRYPOINT ["java","-jar","app.war"]