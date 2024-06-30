package com.example.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.example.dto.StudentDto;
import com.example.enums.UserRole;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String fatherName;
	private String motherName;
	private String StudentClass;
	private Date dob;
	private String address;
	private String gender;
	private UserRole role;
	
	public User() {
		
	}
	
	public User(Long id,String name,String email,String password) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.password=password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getStudentClass() {
		return StudentClass;
	}
	public void setStudentClass(String studentClass) {
		StudentClass = studentClass;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role=role;
	}
	
	public StudentDto getStudentDto() {
		StudentDto studentDto=new StudentDto();
		studentDto.setId(id);
		studentDto.setName(name);
		studentDto.setEmail(email);
		studentDto.setAddress(address);
		studentDto.setDob(dob);
		studentDto.setStudentClass(StudentClass);
		studentDto.setGender(gender);
		studentDto.setFatherName(fatherName);
		studentDto.setMotherName(motherName);
		
		return studentDto;
	}
}
