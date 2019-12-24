package com.example.qiutt.demo;

import com.example.qiutt.demo.async.AsyncExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		try {

			SpringApplication.run(DemoApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
