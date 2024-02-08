FROM node:21-alpine3.18

WORKDIR /gui

RUN apk add bash

VOLUME /gui

CMD (sed -i 's/\r$//' ./run.sh) && (chmod +x ./run.sh) && (./run.sh)