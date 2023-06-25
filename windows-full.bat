docker-compose down
call mvn clean
call mvn compile
call mvn -DskipTests=true clean install
docker-compose build
docker-compose up
