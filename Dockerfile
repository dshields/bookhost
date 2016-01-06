FROM tomee:8-jre-7.0.0-M1-plus

ADD build/libs/mp3.war /usr/local/tomee/webapps/mp3.war
ADD ./src/test/resources/music/ /music
