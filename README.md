# todo_2019

Setup:
```
docker run --name postgresdb -e POSTGRES_PASSWORD=password -e POSTGRES_USER=matthias -e POSTGRES_DB=mydb -p 5432:5432 -d postgres:latest
```
in dir todolist:
```
mvn spring-boot:run -Dspring.profiles.active=prod
```
