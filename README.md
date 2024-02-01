# Project Management #

### Introduction ###

Project Management for a school project. Backend made in Java, gui made with electron and react, cli made with python. Database in files.

## Use Cases

-   [ ] User can see an `input` field where he can type in a to-do item
-   [ ] By pressing send, the User can submit the to-do item and can see that being added to a list of to-do's
-   [ ] User can mark a to-do as `completed`
-   [ ] User can remove a to-do item by pressing on a button (or on the to-do item itself)
-   [ ] User can see a list with all completed to-do's
-   [ ] User can see a list with all active to-do's

### How to I setup my development environment? ###

* Install [Docker](https://www.docker.com)
* Create the necessary Docker Images and Containers by running the following command in the project's root folder:
```sh
docker-compose up --build
```
* *Note:* you can use the **-d** flag to run all the containers in background. If not used, all the containers will run attached to the same process.

#### App container ####

* Entering the app container:
```sh
docker-compose exec app bash
```

* Running the app:
```sh
./compile
```

#### Db container #####
* Entering the db container:
```sh
docker-compose exec db bash
```

* Access the postgresql database:
```sh
psql -U gabriel -d project -h localhost
```

* In case you have the psql in you host machine:
```sh
psql -U gabriel -d project -h 10.0.0.2
```

#### Gui application ####

* Start the gui container:
```sh
docker-compose exec gui bash && npm run dev
```

* Then start the react server
```sh
npm run dev
```

* After the react app running, run the electron:
```sh
cd electron && npm start
```

* *Note:* if the containers are running in the console, CTRL+C signal will stop all the containers. If they are running in background, you can use the following command:
```sh
docker-compose down
```
* Open the next url in your web browser and have fun:
```
http://10.0.0.5
```