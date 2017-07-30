package com.triador.springboot;

import com.triador.springboot.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.text.ParseException;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.triador.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class XUsersApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(XUsersApplication.class, args);
	}
}
