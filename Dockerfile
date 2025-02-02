ARG alpine_version=latest
FROM alpine:${alpine_version}
ARG buildno=1

RUN apk update && \
    apk add --no-cache curl bash

# Download OpenJDK 23 tarball from OpenJDK site
RUN curl -L https://download.oracle.com/java/23/latest/jdk-23_linux-aarch64_bin.tar.gz -o jdk-23_linux-aarch64_bin.tar.gz

# Extract and install OpenJDK 23
RUN mkdir -p /opt/openjdk && \
    tar -xvzf jdk-23_linux-aarch64_bin.tar.gz -C /opt/openjdk && \
    rm jdk-23_linux-aarch64_bin.tar.gz

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/opt/openjdk/jdk-23

# Add JAVA_HOME/bin to PATH
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /
WORKDIR app
WORKDIR build

RUN cd .. \
    && wget https://dlcdn.apache.org/maven/maven-4/4.0.0-rc-2/binaries/apache-maven-4.0.0-rc-2-bin.tar.gz \
    && tar -xvzf apache-maven-4.0.0-rc-2-bin.tar.gz \
    && rm apache-maven-4.0.0-rc-2-bin.tar.gz