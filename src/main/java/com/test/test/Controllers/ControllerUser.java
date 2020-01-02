package com.test.test.Controllers;




import com.sun.tools.javac.util.Pair;
import com.test.test.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ControllerUser extends ControllerMain {

    private static final Logger log = Logger.getLogger(ControllerUser.class);

    @GetMapping("/main")
    public String main(Model model) {
        log.info("Запрос на получение главной страници");
        model.addAttribute("name", "");
        return "main";
    }

    @GetMapping(value = "/all")
    public String getAllUsers(Model model) {
        log.info("запрос на получение страницы со всеми пользователями");
        model.addAttribute("list", serviceUser.getAllUsers());
        return "all";
    }

    @PostMapping(value = "/main")
    public String saveNewUser(@RequestParam String name, @RequestParam String secondname, @RequestParam String email, Model model) {
        log.info("Сохранение нового пользователя");

        User user = serviceUser.saveNewUser(name, secondname, email);
        model.addAttribute("name", user.getId());

        return "main";
    }

    @GetMapping("/getUser")
    public String getUser(Model model) {
        model.addAttribute("name", "");
        return "getUser";
    }

    @PostMapping(value = "/getUser")
    public String getUser(@RequestParam Integer id, Model model) {
        log.info("Получение пользователя по id:" + id);

        User user = serviceUser.getUserById(id);
        model.addAttribute("name", (user == null) ? "Пользовтель не найден." : user);

        return "getUser";
    }

    @GetMapping("/setUser")
    public String setUser(Model model) {
        model.addAttribute("name", "");
        return "setUser";
    }

    @PostMapping(value = "/setUser")
    public String setUser(@RequestParam Integer id, @RequestParam String status, Model model) {
        log.info("Изменение пользователя с id:" + id);

        if ("Online".equals(status) || "Offline".equals(status)) {
            Pair<User, String> pair = serviceUser.changeUserByIdAndStatus(id, status);
            if (pair.fst == null)
                model.addAttribute("name", "Пользовтель не найден.");
            else {
                model.addAttribute("old_status", pair.snd);
                model.addAttribute("id", pair.fst.getId());
                model.addAttribute("new_status", pair.fst.getStatus());
            }
        } else {
            model.addAttribute("name", "Введено не верное состояние");
        }

        return "setUser";
    }

}

