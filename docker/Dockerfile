FROM eclipse-temurin:21

WORKDIR /opt

RUN curl -OL https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.48/bin/apache-tomcat-10.1.48.tar.gz

RUN tar zxvf apache-tomcat-10.1.48.tar.gz

COPY ./matsupyapi-0.0.1-SNAPSHOT.war /opt/apache-tomcat-10.1.48/webapps/matsupyapi.war

EXPOSE 8080
ENTRYPOINT [ "/opt/apache-tomcat-10.1.48/bin/catalina.sh" , "run" ]