package com.example.filedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.filedemo.repository.*;
import com.example.filedemo.model.User;
import com.example.filedemo.model.Teacher;
@Service
public class StudentService implements UserService{
	@Autowired
    private JdbcTemplate jdbcTemplate;  
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherrepository;
    
	public void saveLog(String email, String password, String name) {
		User userNP = new User();
		userNP.setEmail(email);
        userNP.setPassword(password);
        userNP.setName(name);
        userRepository.save(userNP);
		//String query="insert into users(email,name,password) values(?,?)",email, name, password;  
        //jdbcTemplate.update("insert into users(id,email,name,password) values(?,?,?,?)",id,email, name,password); 
	}
}
