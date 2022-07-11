# Getting Started

### Reference Documentation
Requirements
* docker (https://docs.docker.com)
* docker-compose (https://docs.docker.com/compose/install)

### Start project with Docker (recommended)
Running project with docker:

* git clone https://github.com/ashkanzng/DZM.git
* cd DZM
* docker-compose up
* open http://localhost:8080/swagger-ui/index.html#/company-resource

### Start project without Docker
* git clone https://github.com/ashkanzng/DZM.git
* cd DZM/app
* docker run -d --name db-dzm -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=db-dzm -p 5432:5432 postgres:11
* ./mvnw spring-boot:run
* open http://localhost:8080/swagger-ui/index.html#/company-resource


