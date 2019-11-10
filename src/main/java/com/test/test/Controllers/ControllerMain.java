package com.test.test.Controllers;

import com.test.test.model.Users;
import com.test.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ControllerMain {
    public final int time = 20;

    @Autowired
    public UsersRepository usersRepository;

}
