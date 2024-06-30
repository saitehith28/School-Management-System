package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.StudentLeave;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave,Long> {

}
