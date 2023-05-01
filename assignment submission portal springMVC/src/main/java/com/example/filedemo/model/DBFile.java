package com.example.filedemo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DBFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;
    
    @Lob
    private byte[] data;
    private String student_id;
    //private String student_id;
    private static DBFile instance = null;
 
    public DBFile() {

    }

    private DBFile(String fileName, String fileType, byte[] data,String student_id) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
       this.student_id=student_id;
    }
    public static DBFile getInstance(String fileName, String fileType, byte[] data,String student_id){
    	if (instance != null)
        {

        }

        instance = new DBFile(fileName,fileType,data,student_id);
        return instance;
     }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
