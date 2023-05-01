package com.example.filedemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.filedemo.model.User;

@Repository
public interface UserRepository 
       extends JpaRepository<User, String> {
   
	 User findByEmail(String email);
}
