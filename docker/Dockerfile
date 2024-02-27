# Dependencies Cache
FROM maven:3.9.6-amazoncorretto-17-debian as DEPS

WORKDIR /project

COPY ./pom.xml ./pom.xml

RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline


# Build
FROM maven:3.9.6-amazoncorretto-17-debian as BUILDER

WORKDIR /project

COPY --from=DEPS /root/.m2 /root/.m2
COPY --from=DEPS /project/ /project
COPY ./src ./src

RUN mvn clean install -Dmaven.javadoc.skip=true -DskipTests

# Runner
FROM amazoncorretto:17.0.10-alpine3.19
WORKDIR /project
COPY --from=BUILDER /project/target/attendance-0.0.1-SNAPSHOT.jar ./attendance-0.0.1-SNAPSHOT.jar
