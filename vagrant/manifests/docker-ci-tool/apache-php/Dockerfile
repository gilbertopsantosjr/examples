FROM ubuntu:14.04

MAINTAINER Gilberto Santos <gilbertopsantosjr@gmail.com>

USER root 

RUN apt-get update && apt-get upgrade -y

COPY setup-mysql.sh  /
RUN bash -c "/setup-mysql.sh"

RUN apt-get install -y apache2
RUN apt-get install -y php5 php5-common php5-cli php5-mysql php5-curl 

COPY sites /var/www/html/

CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]

EXPOSE 80
EXPOSE 3306
