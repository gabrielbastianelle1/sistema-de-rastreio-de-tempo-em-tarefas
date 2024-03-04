FROM node:21-alpine3.18

WORKDIR /app

COPY . .

RUN apk add bash
RUN npm install -g @angular/cli

ARG PORT=3000
ENV PORT=$PORT

EXPOSE $PORT

CMD (sed -i 's/\r$//' ./run.sh) && (chmod +x ./run.sh) && (./run.sh)