FROM ubuntu
LABEL maintainer="Roland"
RUN apt-get update -y
RUN apt-get install -y curl apache2
RUN apt-get install wget -y
RUN wget https://download.oracle.com/java/19/latest/jdk-19_linux-x64_bin.deb
RUN apt-get -qqy install ./jdk-19_linux-x64_bin.deb -y
RUN update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-19/bin/java 1919
RUN rm -rf /var/lib/apt/lists/*
COPY build/libs/calculator-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]

#RUN apt-get install -y curl apache2
#RUN DEBIAN_FRONTEND=noninteractive apt-get install -y mysql-server
#RUN apt-get clean -y
#RUN rm -rf /var/lib/apt/lists/*
#COPY script.sh /var/www/html
#RUN chmod +x /var/www/html/script.sh
#ADD archive.tar.gz /var/www/html
#EXPOSE 80
#ENTRYPOINT /var/www/html/script.sh