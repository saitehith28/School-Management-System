package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.StudentDto;
import com.example.entities.User;
import com.example.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByRole(UserRole userRole);

	Optional<User> findFirstByEmail(String email);
	
	List<User> findAllByRole(UserRole userRole);
}
