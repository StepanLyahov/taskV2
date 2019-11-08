package com.test.test.repository;

import com.test.test.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findByStatus(String status);
    List<Users> findByTimeChangeGreaterThan(Date date);
}