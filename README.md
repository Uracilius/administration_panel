
# Execution prerequisites:
Angular CLI installed

Node.js installed

Java SDK 19

PostgreSQL database setup

# Execution instructions:
## Frontend:

inside of the 'front' folder, run:

        $ npm install
        $ ng serve -c local -o


## Backend:
open project back/admin-page:
Configure src/main/resources/application.properties to forward requests into your database

This is how your application.properties should look like:

        spring.application.name=admin-page
        spring.datasource.url=${PLACEYOURDATASOURCEURLHERE}
        spring.datasource.username=${PLACEYOURDATASORUCEUSERNAMEHERE}
        spring.datasource.password=${PLACEYOURDATASOURCEPASSWORDHERE}
        spring.datasource.driver-class-name=org.postgresql.Driver
        spring.mvc.dispatch-options-request=true


### Provided below are commands you are to run to start the project. You could run mvn clean install only once.

        $ mvn clean install
        $ mvn spring-boot:run
    

