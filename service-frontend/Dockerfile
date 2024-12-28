FROM node:21-alpine3.18

WORKDIR /app

# Create a non-root user and set permissions
# RUN adduser -D -u 1001 appuser
# RUN chown -R appuser:appuser /app
# USER appuser

COPY . .

# Set the umask
RUN apk add bash
RUN echo "umask 0030" >> /root/.bashrc

RUN npm install -g @angular/cli@16.2.14

# ARG is variable just in image build process
# if no value is passed, the default will be 3000
ARG PORT=3000
# ENV is variable that will be inside the container, it takes the value of the ARG
ENV PORT=$PORT

EXPOSE $PORT

VOLUME [ "/app" ]

CMD (sed -i 's/\r$//' ./run.sh) && (chmod +x ./run.sh) && (./run.sh)
