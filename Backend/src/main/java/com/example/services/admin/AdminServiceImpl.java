package com.example.services.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.repositories.FeeRepository;
import com.example.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import com.example.dto.FeeDto;
import com.example.dto.SingleStudentDto;
import com.example.dto.StudentDto;
import com.example.entities.Fee;
import com.example.entities.User;
import com.example.enums.UserRole;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepository;
	
	private final FeeRepository feeRepository;
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount=userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount==null) {
			User admin=new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
		}
		
	}

	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findFirstByEmail(studentDto.getEmail());
		if(optionalUser.isEmpty()) {
			User user=new User();
			BeanUtils.copyProperties(studentDto,user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser=userRepository.save(user);
			StudentDto createdStudentDto=new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
			
		}
		return null;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		// TODO Auto-generated method stub
		return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(studentId);
	}

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentId);
		SingleStudentDto singleStudentDto=new SingleStudentDto();
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentId);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			user.setName(studentDto.getName());
			user.setGender(studentDto.getGender());
			user.setAddress(studentDto.getAddress());
			user.setDob(studentDto.getDob());
			user.setStudentClass(studentDto.getStudentClass());
			user.setFatherName(studentDto.getFatherName());
			user.setMotherName(studentDto.getMotherName());
			user.setEmail(studentDto.getEmail());
			User updatedStudent=userRepository.save(user);
			StudentDto updatedStudentDto=new StudentDto();
			updatedStudentDto.setId(updatedStudent.getId());
			return updatedStudentDto;
		}
		return null;
	}

	@Override
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalStudent=userRepository.findById(studentId);
		if(optionalStudent.isPresent()) {
			Fee fee=new Fee();
			fee.setAmount(studentId);
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreatedDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee=feeRepository.save(fee);
			FeeDto paidFeeDto=new FeeDto();
			paidFeeDto.setId(paidFee.getId());
			return paidFeeDto;
		}
		return null;
	}
}
