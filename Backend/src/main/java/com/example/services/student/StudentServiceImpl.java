package com.example.services.student;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.SingleStudentDto;
import com.example.dto.StudentLeaveDto;
import com.example.entities.StudentLeave;
import com.example.entities.User;
import com.example.enums.StudentLeaveStatus;
import com.example.repositories.UserRepository;
import com.example.repositories.StudentLeaveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final UserRepository userRepository;
	
	private final StudentLeaveRepository studentLeaveRepository;

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentId);
		SingleStudentDto singleStudentDto=new SingleStudentDto();
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentLeaveDto.getUserid());
		if(optionalUser.isPresent()) {
			StudentLeave studentLeave=new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setDate(new Date());
			studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave SubmittedStudentLeave=studentLeaveRepository.save(studentLeave);
			StudentLeaveDto studentLeaveDto1=new StudentLeaveDto();
			studentLeaveDto1.setId(SubmittedStudentLeave.getId());
			return studentLeaveDto1;
		}
		return null;
	}
}
