package com.example.services.admin;

import java.util.List;

import com.example.dto.FeeDto;
import com.example.dto.SingleStudentDto;
import com.example.dto.StudentDto;

public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();

	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);
	
	FeeDto payFee(Long studentId, FeeDto feeDto);

}
