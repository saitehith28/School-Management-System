package com.example.services.student;

import com.example.dto.SingleStudentDto;
import com.example.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long studentId);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

}
