FROM jenkins:1.651.1

MAINTAINER Gilberto Santos <gilbertopsantosjr@gmail.com>

USER root

RUN apt-get update \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*

# Install Gradle
RUN wget https://services.gradle.org/distributions/gradle-2.13-bin.zip
RUN sudo unzip gradle-2.13-bin.zip
RUN sudo mv gradle-2.13 /opt/
RUN sudo rm gradle-2.13-bin.zip

# Install Ant Apache
RUN wget http://archive.apache.org/dist/ant/binaries/apache-ant-1.8.4-bin.tar.gz
RUN sudo tar -xvzf apache-ant-1.8.4-bin.tar.gz
RUN sudo mv apache-ant-1.8.4 /opt/
RUN sudo rm apache-ant-1.8.4-bin.tar.gz

ENV GRADLE_HOME /opt/gradle-2.13
ENV ANT_HOME /opt/apache-ant-1.8.4

ENV PATH $PATH:$GRADLE_HOME/bin
ENV PATH $PATH:$ANT_HOME/bin

RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

USER jenkins

COPY plugins.txt /var/jenkins_home/plugins.txt
RUN /usr/local/bin/plugins.sh /var/jenkins_home/plugins.txt

# Adding default Jenkins Jobs
COPY jobs/cadmea.xml /usr/share/jenkins/ref/jobs/cadmea/config.xml
# COPY jobs/2-job-dsl-seed-job.xml /usr/share/jenkins/ref/jobs/2-job-dsl-seed-job/config.xml
# COPY jobs/3-conference-app-seed-job.xml /usr/share/jenkins/ref/jobs/3-conference-app-seed-job/config.xml
# COPY jobs/4-selenium2-seed-job.xml /usr/share/jenkins/ref/jobs/4-selenium2-seed-job/config.xml
# COPY jobs/5-docker-admin-seed-job.xml /usr/share/jenkins/ref/jobs/5-docker-admin-seed-job/config.xml

############################################
# Configure Jenkins
############################################
# Jenkins settings
COPY config/config.xml /usr/share/jenkins/ref/config.xml

# Jenkins Settings, i.e. Maven, Groovy, ...
COPY config/hudson.tasks.Maven.xml /usr/share/jenkins/ref/hudson.tasks.Maven.xml
COPY config/hudson.plugins.groovy.Groovy.xml /usr/share/jenkins/ref/hudson.plugins.groovy.Groovy.xml
COPY config/maven-global-settings-files.xml /usr/share/jenkins/ref/maven-global-settings-files.xml

# SSH Keys & Credentials
COPY config/credentials.xml /usr/share/jenkins/ref/credentials.xml
COPY config/ssh-keys/id_rsa_github /usr/share/jenkins/ref/.ssh/id_rsa
COPY config/ssh-keys/id_rsa_github.pub /usr/share/jenkins/ref/.ssh/id_rsa.pub


