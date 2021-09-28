
**Dockerising Spring Boot application with Database**

Download and install Docker for windows/Linux/mac from the official website [https://hub.docker.com/](https://hub.docker.com/)

 - Clone the above Demo Spring Boot Application.
 - After installation in order to check whether the Docker is running or
   not
 

Then open the “**DockerFile**” from the project folder
This contains the commands for generating Docker image

    FROM openjdk:8  
    ADD target/docker-spring-boot-mysql.jar docker-spring-boot-mysql.jar  
    EXPOSE 8083  
    ENTRYPOINT ["java", "-jar" , "docker-spring-boot-mysql.jar" ]

**FROM** will pull openjdk from Docker repo.

**ADD** will copy build jar form target folder to root folder of the Docker.

**EXPOSE** will expose the port 8083.

**ENTRYPOINT** will execute the command given.
(Change the target jar name if you want)

** Mysql is the dependency for this project. so we need
to get mysql pull from Docker repo.

Build project and create jar file 
`mvn clean install -DskipTests`

Create docker image this project
`docker build -f DockerFile -t docker-spring-boot-mysql .`
From the cmd enter the command where –f <FILENAME>
-t <tag-name-for-Docker-image> and **‘.’** is the file path that is current as of now.

This will execute the commands from the docker file. Initially running may take some time as it downloads a **openjdk** from the repository.

    docker image ls

This command will give you list of images you have and you can find one with the name **docker-spring-boot-mysql .**

**Setup MySQL**

Now we will create and run an image of the MySQL database. From our terminal, we will run the below command. Here, -d in this command indicates that the Docker command will run in detached mode.

    docker run -d  -p  6033:3306 --name=mysql-docker --env="MYSQL_ROOT_PASSWORD=root"  --env="MYSQL_PASSWORD=root"  --env="MYSQL_DATABASE=spring_crud" mysql

Hopefully, the MySQL image is pulled and running as a container. To check this, we can run.

You can check that using `docker container ls` command.

Now we can check by logging in to MySQL.

    docker exec -it mysql-docker bash
*(mysql-docker is the tag name we have given while creating)*
Now you have the databse created inside the docker mysql

You can import the sql by following command

    docker exec -i docker-mysql mysql -uroot -proot spring_crud <demo.sql

**Running the Project inside the Docker**

`docker run -t --link mysql-docker:mysql -p 8083:8083 docker-spring-boot-mysql`

**--link** will link the MySQL container and will be exposing the port 8083

Now it will open up in

    http://localhost:8083/demo/swagger-ui.html

POST 

    {
    "username":"anyname"
    }
