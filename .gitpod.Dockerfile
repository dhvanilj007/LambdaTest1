# .gitpod.Dockerfile
FROM gitpod/workspace-full:latest

# Install Java 17 + Maven
USER root
RUN apt-get update && apt-get install -y maven openjdk-17-jdk && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

USER gitpod