package com.test.test.Controllers;

import com.test.test.model.Users;
import com.test.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ControllerMain {
    public final int time = 1000;

    @Autowired
    public UsersRepository usersRepository;

    public String listToString(List<Users> list) {
        String res;
        if (list.size() > 0) {
            res = "";
            for (Users u : list) res = res + u  + " ";
        } else
            res = "Пусто";

        return  res;
    }
}
