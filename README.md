# Assignment-Submission-Portal-OOAD
<p align="center">
<a href=""><img title="Spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"></a>
<a href=""><img title="Thymeleaf" src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white"></a>
<a href=""><img title="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"></a>
<a href=""><img title="MySQL" src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white"></a>
<a href=""><img title="Maven" src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white"></a>
</p>
Assignment Submission Portal developed using Java and Spring Boot allows students to submit their assignments online. Aims to simplify the process of submitting assignments for both students and teachers.

## Design Patterns
- Singleton 
- Factory method
- Memento
## Design Principles
- Single Responsibility Principle
- Open Close Principle
- Dependency Inversion Principle
- Interface Segregation Principle

## Setup Details
### Clone the repository

```bash
git clone https://github.com/Nireekshadrai/File-Submission-Portal-OOAD.git
```

### Configure MySQL

Create a MySQL database named `assignment_submission`, and change the username and password in `src/main/resources/application.properties` as per your MySQL
installation -

```properties
spring.datasource.username= <MYSQL USERNAME>
spring.datasource.password= <MYSQL PASSWORD>
spring.resources.static-locations= <RESOURCE LOCATION>
```

### MySQL setup
Create tables `User` to store Student details, `Teachers` to store Teacher details and `Files` to store assignment document deatils -
```bash
create table users (id varchar(255) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));

create table teachers (id varchar(255) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));

create table files (id varchar(255) not null, data longblob, file_name varchar(255), file_type varchar(255), student_id varchar(255),primary key (id), foreign key(student_id) references users(id));
```
### Run the app

```bash
cd assignment submission portal springMVC
mvn spring-boot:run
```

The application can be accessed at `http://localhost:8080`

