# team-management-sample

# to build the application
mvn install

# build with docker
docker build -t sample/team-mgmnt-docker .

# running app with docker
docker run --rm --name team-mgmnt -p 8090:8080 sample/team-mgmnt-docker

# running app and mysql database with docker-compose
docker-compose up --build
