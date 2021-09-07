# find step by Step explaination bellow
https://medium.com/@anushkadarr/practical-approach-to-java-rest-api-timezone-handling-with-angular-springboot-mysql-42396e823787

![clientLook](https://user-images.githubusercontent.com/2859878/132262407-e560ce9c-999c-4bc2-af32-9f34d69938a4.png)


# Checkout and setup

You may start checking out this repository from GitHub. https://github.com/anushkadar/TimeZoneClientServer.git

Basically, the repository t contains two projects, one for Angular Front end client and the other for Spring boot Server application.
There I have setup 3 persistence frameworks including
Spring Data JPA, Spring JDBC Template, Plan JDBC
Also configurable to run against Mysql Server and Postgres Server.

There are several ways to run a java app in a specific time zone.
The simplest way is to add the VM argument via the IDE you are running it.
Ex: -Duser.timezone=Europe/London
## Setting up the DB
I have added scripts to create databases (MySQL and PostgreSQL) in Sever Application project /resources directory.

![resource](https://user-images.githubusercontent.com/2859878/132262433-84455b5d-bcf9-4177-9a10-e31e525007cf.png)

Database Scripts
If you are familiar with Docker, the simplest way is to execute given Docker compose files separately.
command: docker-compose up
This will download the database image from the docker hub and will start the database container by creating the database.
Then You need to connect to the database and execute create DB script.
Otherwise, you may create the DB and table with existing local DB or can install DB separately
## Setting up the Server
As I mentioned earlier server is a spring boot Maven project so once your server is up and running you may start the application using your favorite IDE.

Connecting to which DB is configurable in application.properties.
Change the value in spring.datasource.url property according to the configured DB
## Setting up the Client
You need Node server installed if you do not yet before proceeding to set up the client. Then it's just going to the client directory of your project via command prompt and just executes the command
npm install
You may suggest fixing vulnerabilities using
npm audit-fix or
npm audit-fix — force
no worries just follow the suggested instructions
Finally, you can start the client with
npm start

Enjoy..
