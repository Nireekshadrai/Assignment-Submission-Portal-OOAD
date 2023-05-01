package com.example.filedemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.filedemo.model.*;
import com.example.filedemo.repository.UserRepository;
@Service
public class FactoryUserService {
	@Autowired
    TeacherService teacherService;
	@Autowired
    StudentService studentService;
	public void saveUser(String email,String password, String name,String user) {

        
        if(user.equalsIgnoreCase("teacher")) {
        	
            teacherService.saveLog(email,password,name);
        } else if(user.equalsIgnoreCase("student")) {
            studentService.saveLog(email,password,name);
        
        }
}
}