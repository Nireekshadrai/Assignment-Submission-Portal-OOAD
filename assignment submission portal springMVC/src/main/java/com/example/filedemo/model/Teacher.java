package com.example.filedemo.model;



import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;
    public Teacher() {

    }
    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
        
    }

    public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
	this.email=email;}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
	this.name=name;}
	
	}
