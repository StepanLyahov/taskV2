package com.test.test.Controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ControllerFile extends ControllerMain {

    private static final Logger log = Logger.getLogger(ControllerFile.class);
    public final static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @GetMapping("/loadImage")
    public String loadImage(Model model) {
        return "loadImage";
    }

    @PostMapping("/loadImage")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
        log.info("Загружаем файл на сервер");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()).append(" ");
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("name", "URI:  " + uploadDirectory + "/" + fileNames.toString());

        return "loadImage";
    }
}
