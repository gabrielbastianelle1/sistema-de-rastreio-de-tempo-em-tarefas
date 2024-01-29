FROM postgis/postgis:16-3.4-alpine

ENV POSTGRES_USER=gabriel
ENV POSTGRES_PASSWORD=123
ENV POSTGRES_DB=project

COPY initial_schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

VOLUME [ "/var/lib/postgresql/data" ]