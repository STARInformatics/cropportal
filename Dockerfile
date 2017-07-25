FROM openjdk:8

ENV PROJECTNAME cropportal

RUN wget -q https://services.gradle.org/distributions/gradle-3.4.1-bin.zip && \
    unzip gradle-3.4.1-bin.zip -d /opt && \
    rm gradle-3.4.1-bin.zip && \
    mkdir /home/$PROJECTNAME

ENV PATH $PATH:/opt/gradle-3.4.1/bin/

COPY . /home/$PROJECTNAME/

RUN cd home/$PROJECTNAME && \
    gradle clean -x test && \
    gradle build -x test

WORKDIR /home/$PROJECTNAME/web

ENTRYPOINT java -jar build/libs/traits-website-web-*.jar
