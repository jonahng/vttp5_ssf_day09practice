package com.jonah.vttp5_ssf_day09practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonah.vttp5_ssf_day09practice.Service.ToDoRestService;
import com.jonah.vttp5_ssf_day09practice.Service.ToDoService;

@SpringBootApplication
public class Vttp5SsfDay09practiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Vttp5SsfDay09practiceApplication.class, args);
	}


	@Autowired
	ToDoRestService toDoRestService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		toDoRestService.loadToDoJson();
		System.out.println("finished loading json file to redis");
	}

}
