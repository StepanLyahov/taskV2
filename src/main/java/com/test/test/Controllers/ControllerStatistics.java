package com.test.test.Controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ControllerStatistics  extends ControllerMain {

    private static final Logger log = Logger.getLogger(ControllerStatistics.class);

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        model.addAttribute("result", "");
        return "statistics";
    }

    @PostMapping(value = "/statistics")
    public String postStatistics(@RequestParam String status, @RequestParam String date, Model model) {
        log.info("Запрос статистики сервера");

        if (status.isEmpty() && date.isEmpty()) {
            // если фильтр пришёл пустой, то просто выводим всех пользователей
            model.addAttribute("result", serviceUser.getAllUsers());
        } else if (!status.isEmpty() && date.isEmpty()) {
            // если статус присутствует, а время нет, то просто выводим все пользовтелей с этим статусом
            if ("Online".equals(status) || "Offline".equals(status))
                model.addAttribute("result", serviceUser.findUsersByStatus(status));
            else
                model.addAttribute("result", "В поле статус введено некорректное значение");
        } else {
            DateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                Date currentDate = dateForm.parse(date); // получили текущее время запроса
                model.addAttribute("result", serviceUser.filterUserByStatusAndDate(status, currentDate));
            } catch (Exception ex) {
                model.addAttribute("result", "Введён неверный тип даты");
            }
        }

        return "statistics";
    }

}
