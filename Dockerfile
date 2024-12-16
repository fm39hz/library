# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-23-alpine AS maven_build
WORKDIR /app

COPY pom.xml .
COPY src ./src

# build the app and download dependencies only when these are new (thanks to the cache)
RUN mvn clean package -Dmaven.test.skip

# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/dependency && cd target/dependency && jar -xf ../*.jar

# Stage 2: Run the application
FROM openjdk:23-jdk-slim
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

#copy built app layer by layer

ARG DEPENDENCY=target/dependency
COPY --from=maven_build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=maven_build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=maven_build ${DEPENDENCY}/META-INF /app/META-INF
ENTRYPOINT ["java","-cp","app:app/lib/*","library.LibraryApplication"]