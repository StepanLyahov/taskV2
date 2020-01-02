package com.test.test.Controllers;

import com.test.test.Services.ServiceUser;
import com.test.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ControllerMain {
    public final int time = 20;

    @Autowired
    protected UsersRepository usersRepository;

    @Autowired
    public ServiceUser serviceUser;

}
