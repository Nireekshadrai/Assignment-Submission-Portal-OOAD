package com.example.filedemo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.filedemo.model.Teacher;

@Repository
public interface TeacherRepository 
       extends JpaRepository<Teacher, String> {
   
	 Teacher findByEmail(String email);
}
