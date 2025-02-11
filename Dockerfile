FROM alpine:latest

RUN apk add --no-cache \
    wget \
    curl \
    unzip \
    git \
    openjdk21 \
    maven

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="$MAVEN_HOME/bin:$PATH"

WORKDIR /app

COPY . /app

RUN mvn clean package

CMD ["mvn", "compile"]