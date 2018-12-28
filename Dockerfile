FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD hello-0.0.1-SNAPSHOT.jar /app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"," jar","app.jar"]
