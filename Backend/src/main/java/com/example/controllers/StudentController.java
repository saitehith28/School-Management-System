package com.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SingleStudentDto;
import com.example.dto.StudentLeaveDto;
import com.example.services.student.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto=studentService.getStudentById(studentId);
		if(singleStudentDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(singleStudentDto);
	}
	
	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto){
		StudentLeaveDto submittedStudentLeaveDto=studentService.applyLeave(studentLeaveDto);
		if(submittedStudentLeaveDto==null)
			return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentLeaveDto);
	}
}
