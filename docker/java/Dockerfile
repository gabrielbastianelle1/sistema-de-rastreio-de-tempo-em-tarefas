FROM openjdk:19-jdk-alpine3.16

WORKDIR /app

# install bash
RUN apk add bash

# install nodemon
RUN apk add --update nodejs npm
RUN npm update npm -g
RUN npm install nodemon -g

# install mavemon (to relaod maven app)
RUN npm install -g mavenmon

# installing inotify-tools
RUN apk add inotify-tools


#
RUN apk add g++

# install maven
RUN apk add maven



VOLUME [ "/app" ]

CMD (sed -i 's/\r$//' ./run.sh) && (chmod +x ./run.sh) && (./run.sh)