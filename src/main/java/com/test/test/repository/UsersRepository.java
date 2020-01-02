package com.test.test.repository;

import com.test.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findByStatus(String status);
    List<User> findByTimeChangeGreaterThan(Date date);
}

