package com.test.test.Controllers;

import com.test.test.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControllerStatistics  extends ControllerMain{

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        model.addAttribute("result", "");
        return "statistics";
    }

    @PostMapping(value = "/statistics")
    public String postStatistics(@RequestParam String status, @RequestParam String date, Model model) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (status.equals("") && date.equals("")) { // если фильтр пришёл пустой, то просто выводим всех пользователей
            model.addAttribute("result", listToString(usersRepository.findAll()));
        } else if (!status.equals("") && date.equals("")) { // если статус присутствует, а время нет, то просто выводим все пользовтелей с этим статусом
            model.addAttribute("result", listToString(usersRepository.findByStatus(status)));
        } else {
            DateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                List<Users> newList = new ArrayList<>();
                Date currentDate = dateForm.parse(date); // получили текущее время запроса
                //симулируем долгий запрос
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Users> usersList = usersRepository.findByTimeChangeGreaterThan(currentDate);

                for (Users users : usersList) {
                    if (users.getStatus().equals(status))
                        newList.add(users);
                }
                model.addAttribute("result" , listToString(newList));


            } catch (ParseException e) {
                model.addAttribute("result" , "Введён неверный тип даты");
                return "statistics";
            }
        }

        return "statistics";
    }

}
