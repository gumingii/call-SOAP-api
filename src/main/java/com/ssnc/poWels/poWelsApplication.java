package com.ssnc.poWels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class poWelsApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(poWelsApplication.class, args);
        ctx.close();
	}
}
