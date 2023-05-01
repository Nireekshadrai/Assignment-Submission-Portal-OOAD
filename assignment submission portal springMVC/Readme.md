##Assignment Submission Portal

## Setup

### Clone the repository

```bash
git clone https://github.com/Nireekshadrai/File-Submission-Portal-OOAD.git
cd assignment submission portal springMVC
```

### Configure MySQL

Create a MySQL database named `assignment_submission`, and change the username and password in `src/main/resources/application.properties` as per your MySQL
installation -

```properties
spring.datasource.username= <MYSQL USERNAME>
spring.datasource.password= <MYSQL PASSWORD>
```

### MySQL setup
Create tables `User` to store Student details, `Teachers` to store Teacher details and `Files` to store assignment document deatils -
```bash
create table users (id varchar(255) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));

create table teachers (id varchar(255) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id));

create table files (id varchar(255) not null, data longblob, file_name varchar(255), file_type varchar(255), student_id varchar(255),primary key (id), foreign key(student_id) references users(id));
```
