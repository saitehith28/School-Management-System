package com.example.controllers;

import com.example.dto.FeeDto;
import com.example.dto.SingleStudentDto;
import com.example.dto.StudentDto;
import com.example.services.admin.AdminService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService=adminService;
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
		StudentDto createdStudentDto=adminService.postStudent(studentDto);
		if(createdStudentDto==null)
			return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> allStudents=adminService.getAllStudents();
		return ResponseEntity.ok(allStudents);
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
		adminService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto=adminService.getStudentById(studentId);
		if(singleStudentDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(singleStudentDto);
	}
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){
		StudentDto createdStudentDto=adminService.updateStudent(studentId,studentDto);
		if(createdStudentDto==null)
			return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
	}
	
	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> payFee(@PathVariable Long studentId, @RequestBody FeeDto feeDto){
		FeeDto paidFeeDto=adminService.payFee(studentId, feeDto);
		if(paidFeeDto==null)
			return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(paidFeeDto);
	}
	
}
