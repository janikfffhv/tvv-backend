docker-compose down
call mvn clean
call mvn compile
call mvn install
docker-compose build
docker-compose up
