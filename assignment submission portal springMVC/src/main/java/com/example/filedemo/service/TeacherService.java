package com.example.filedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.filedemo.model.*;
import com.example.filedemo.repository.TeacherRepository;
@Service
public class TeacherService implements UserService{
	@Autowired
    private JdbcTemplate jdbcTemplate; 
	@Autowired
    TeacherRepository teacherrepository;
	public void saveLog(String email, String password, String name) {
		Teacher userNP = new Teacher();
     userNP.setEmail(email);
   userNP.setPassword(password);
   userNP.setName(name);
   teacherrepository.save(userNP);
	//jdbcTemplate.update("insert into teachers(id,email,name,password) values(?,?,?,?)",id,email, name,password); 
	}
}
