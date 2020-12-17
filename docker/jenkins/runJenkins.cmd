docker run -d --rm --name jenkins -p 8099:8080 -p 50000:50000 -v %cd%\home:/var/jenkins_home jenkins/jenkins:alpine

