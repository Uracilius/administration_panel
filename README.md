
# Execution prerequisites:
Angular CLI installed

Node.js installed

Java SDK 17

PostgreSQL database setup

# Execution instructions:
## Frontend:

inside of the 'front' folder, run:

        $ npm install
        $ ng serve -c local -o


## Backend:
open project back/admin-page:
Configure src/main/resources/application.properties watch into your database

        $ mvn clean install
        $ mvn spring-boot:run
    


# FEATURE CREEP LIST: 

## FRONT: 
Login validation

## Backend:
Session tokens & authentication

