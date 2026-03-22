FROM tomcat:10-jdk17
COPY TelanganaMLAPortal.war /usr/local/tomcat/webapps/
EXPOSE 8080