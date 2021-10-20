FROM jenkins/jenkins:2.264-jdk11

USER root
RUN curl -sSL https://get.docker.com/ | sh
RUN usermod -a -G docker jenkins
RUN apt-get update
RUN apt-get install docker-compose -y
RUN mkdir -p /var/run
RUN touch /var/run/docker.sock
RUN chown jenkins:jenkins /var/run/docker.sock
RUN chmod g+w /var/run/docker.sock
USER jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY seedJob.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
