package com.example.filedemo.controller;

import com.example.filedemo.model.DBFile;
import com.example.filedemo.model.User;
import com.example.filedemo.model.Teacher;
import com.example.filedemo.payload.UploadFileResponse;
import com.example.filedemo.payload.DownloadFileResponse;
import com.example.filedemo.repository.UserRepository;

import com.example.filedemo.repository.TeacherRepository;
import com.example.filedemo.service.*;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import java.util.*;  
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherrepository;
    
    public String student_id; //check this
    @Autowired
    private FactoryUserService factoryUserService;
    @Autowired
    private DBFileStorageService dbFileStorageService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/teacher")
    public ModelAndView teacher(Model model) {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacherchoose.html");
        return modelAndView;
    }
    @RequestMapping("/student")
    public ModelAndView student() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("choosesignlog.html");
        return modelAndView;
    }
    @RequestMapping("/l")
    public ModelAndView l(Model model) {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @RequestMapping("/r")
    public ModelAndView r() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register.html");
        return modelAndView;
    }
    @RequestMapping("/userpass")
    public ModelAndView userpass(@RequestParam String email, @RequestParam String password,@RequestParam String name) {
            	factoryUserService.saveUser(email, password, name,"student");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/l");
        return modelAndView;
        
    }
    @RequestMapping("/log")
    public ModelAndView log(@RequestParam String email, @RequestParam String password) {
        User details = userRepository.findByEmail(email);
        ModelAndView modelAndView1 = new ModelAndView();
        modelAndView1.setViewName("login.html");
        if(details!=null){
          String user = details.getEmail();
          String pass = details.getPassword();
          ModelAndView modelAndView = new ModelAndView();
          student_id=details.getId();
          modelAndView.setViewName("redirect:/upload");
          
       
          if((user.equals(email)) && (pass.equals(password))) return modelAndView;
          else return modelAndView1;
        }
        else return modelAndView1;
        
    }
    @RequestMapping("/tl")
    public ModelAndView tl(Model model) {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacherlogin.html");
        return modelAndView;
    }
    @RequestMapping("/tr")
    public ModelAndView tr() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacherregister.html");
        return modelAndView;
    }
    @RequestMapping("/tuserpass")
    public ModelAndView tuserpass(@RequestParam String email, @RequestParam String password,@RequestParam String name) {
    	factoryUserService.saveUser(email, password, name,"teacher");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/tl");
        return modelAndView;
        
    }
    @RequestMapping("/tlog")
    public ModelAndView tlog(@RequestParam String email, @RequestParam String password) {
        Teacher details = teacherrepository.findByEmail(email);
        ModelAndView modelAndView1 = new ModelAndView();
        modelAndView1.setViewName("teacherlogin.html");
        if(details!=null){
          String user = details.getEmail();
          String pass = details.getPassword();
          ModelAndView modelAndView = new ModelAndView();
          modelAndView.setViewName("redirect:/viewAssignment");
          
       
          if((user.equals(email)) && (pass.equals(password))) return modelAndView;
          else return modelAndView1;
        }
        else return modelAndView1;
        
    }
   
    @RequestMapping("/viewAssignment")
    public ModelAndView viewAssignment() {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewassignment.html");
        return modelAndView;
    }
    @RequestMapping("/upload")
    public ModelAndView upload() {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload.html");
        return modelAndView;
    }
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file,student_id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(),dbFile.getId());
    }
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam("fileid") String FileId) {
    	System.out.println(FileId);
    	dbFileStorageService.deleteFile(FileId);
    	//System.out.println(i);
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/upload");
        return modelAndView;
    }

   /* @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
*/
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
     @GetMapping("/download")
    public DownloadFileResponse download(@PathVariable DBFile file) {
    	 DBFile dbFile = dbFileStorageService.getFile(file.getId());
    	 String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/downloadFile/")
                 .path(dbFile.getId())
                 .toUriString();
    	 String name=dbFile.getFileName();
    	 System.out.println(name);
         return new DownloadFileResponse(name,dbFile.getId(), fileDownloadUrl);
    }

     @GetMapping("/viewMyFiles")
     public List<DBFile> viewMyFiles() {
         // Load file from database
     	List<DBFile> dbFiles = dbFileStorageService.viewMySubmission(student_id);
     	System.out.println(dbFiles);
     	List<String> list=new ArrayList<String>();  
         
         /*dbFiles.forEach((dbFile) -> {
             list.add(dbFile.getFileName());
             download(dbFile.getId());
             System.out.println(dbFile.getFileName());
         });*/
         return dbFiles;
                 
         
     }

    @GetMapping("/viewAllFiles")
    public List<DownloadFileResponse> viewAllFiles() {
        // Load file from database
    	List<DBFile> dbFiles = dbFileStorageService.getAllFile();
        List<String> list=new ArrayList<String>();  
        
        /*dbFiles.forEach((dbFile) -> {
            list.add(dbFile.getFileName());
            download(dbFile.getId());
            System.out.println(dbFile.getFileName());
        });*/
        return dbFiles
                .stream()
                .map(file-> download(file))
                .collect(Collectors.toList());
        
    }
}
