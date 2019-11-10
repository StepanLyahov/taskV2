package com.test.test.Controllers;


import com.test.test.model.Users;
import com.test.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class ControllerUser extends ControllerMain {

    @GetMapping("/main")
    public String mainn(Model model) {
        model.addAttribute("name", "");
        return "main";
    }

    @GetMapping(value = "/all")
    public String all(Model model) {
        model.addAttribute("list", usersRepository.findAll().stream().map(u -> u.toString()));
        return "all";
    }

    @PostMapping(value = "/main")
    public String mainPost(@RequestParam String name, @RequestParam String secondname, @RequestParam String email, Model model) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Users users = new Users(name,secondname,email, "defailt_image", "", null);
        usersRepository.save(users);

        model.addAttribute("name", users.getId());

        return "main";
    }

    @GetMapping("/getUser")
    public String getUser(Model model) {
        model.addAttribute("name", "");
        return "getUser";
    }

    @PostMapping(value = "/getUser")
    public String getUserPost(@RequestParam Integer id, Model model) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Users users = usersRepository.getOne(id);
        model.addAttribute("name", (users == null) ? "Пользовтель не найден." : users);

        return "getUser";
    }

    @GetMapping("/setUser")
    public String setUser(Model model) {
        model.addAttribute("name", "");
        return "setUser";
    }

    @PostMapping(value = "/setUser")
    public String setUser(@RequestParam Integer id, @RequestParam String status, Model model) {
        //TODO Переделать процесс обновления информации пользователя
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Users users = usersRepository.getOne(id);

        if (users == null) {
            model.addAttribute("name", "Пользовтель не найден.");
        } else {
            if ("Online".equals(status) || "Offline".equals(status)) {
                model.addAttribute("old_status", users.getStatus());
                usersRepository.deleteById(id);
                users.setStatus("Online".equals(status) ? "Online" : "Offline");
                users.setTimeChange(new Date());
                usersRepository.save(users);
            } else
                model.addAttribute("name", "Введено не верное состояние");

            model.addAttribute("id", users.getId());
            model.addAttribute("new_status", users.getStatus());
        }
        return "setUser";
    }

}

