# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17-slim as build

# Add Maintainer Info
LABEL maintainer="vonguyenminhnhatanphu@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/diamond_shop-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Environment variables
ENV DIAMOND_DB_URL=jdbc:sqlserver://nstut.database.windows.net:1433
ENV DIAMOND_DB_USER=NsTut812004@nstut
ENV DIAMOND_DB_PASSWORD=123456789@nT
ENV STRIPE_SECRET=sk_test_51PZaB3GvGs3G9mvhpVnwBXSCuj0oIhD6t9xYlSwZVRPYwtjezoWrs4sn49e4BgTStAxWJXfTCL6fVmnUtHiDi5j500mky75p20

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]