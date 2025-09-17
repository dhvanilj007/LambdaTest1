FROM gitpod/workspace-full

# Install OpenJDK 17 and Maven
RUN sudo apt-get update \
 && sudo apt-get install -y openjdk-17-jdk maven \
 && sudo apt-get clean

# Set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH