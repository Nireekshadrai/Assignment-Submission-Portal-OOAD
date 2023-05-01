package com.example.filedemo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
@Service
public interface UserService {
	  
	 public void saveLog(String email, String password, String name);
	    
}
