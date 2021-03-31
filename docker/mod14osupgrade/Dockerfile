FROM artifacts.aa.com/docker-all/ibmcloud:latest
COPY repositories.txt /etc/apk/repositories
RUN apk upgrade --update-cache --available
RUN apk add py-pip python3-dev libffi-dev openssl-dev gcc libc-dev make rust cargo musl-dev
RUN pip install --upgrade pip
RUN pip3 install docker-compose
