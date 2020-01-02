package com.test.test;

import com.test.test.Controllers.ControllerFile;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure(); // стандартная конфигурация логирования
		new File(ControllerFile.uploadDirectory).mkdir();
		SpringApplication.run(TestApplication.class, args);
	}
}
