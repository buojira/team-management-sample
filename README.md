# team-management-sample

# to build the application
mvn install

# to build the docker image of the application

docker build -t sample/team-mgmnt-docker .

# to rund application with docker
docker run --rm --name team-mgmnt -p 8090:8080 sample/team-mgmnt-docker
