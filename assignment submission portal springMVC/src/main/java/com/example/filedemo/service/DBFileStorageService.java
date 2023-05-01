package com.example.filedemo.service;
import java.sql.*; 
import com.example.filedemo.exception.FileStorageException;
import com.example.filedemo.exception.MyFileNotFoundException;
import com.example.filedemo.model.DBFile;
import com.example.filedemo.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    public DBFile storeFile(MultipartFile file,String id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile =DBFile.getInstance(fileName, file.getContentType(), file.getBytes(),id);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    public List<DBFile> getAllFile() {
        return dbFileRepository.findAll();
                
    }
    
    public void deleteFile(String fileId) {
    	String query="delete from files where id='"+fileId+"' ";  
        jdbcTemplate.execute(query); 
    }
    public  List<DBFile> viewMySubmission(String id) {
    	System.out.println(id);
    	String sql="select file_name from files where student_id='"+id+"';";
    	System.out.println(sql);
     List<DBFile> files=jdbcTemplate.query( sql,new BeanPropertyRowMapper(DBFile.class));
     System.out.println(files);
     return files;
    }
    
}
