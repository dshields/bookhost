FROM tomee:8-jre-7.0.0-M1-plus
ADD build/libs/bookhost.war /usr/local/tomee/webapps/bookhost.war
ADD ./src/test/resources /books
ENV JAVA_OPTS -Dbook=/books/book1.mp3
