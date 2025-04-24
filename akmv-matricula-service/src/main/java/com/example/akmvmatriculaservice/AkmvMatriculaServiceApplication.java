package com.example.akmvmatriculaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AkmvMatriculaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkmvMatriculaServiceApplication.class, args);
		System.out.println("INICIADO CORRECTAMENTE");

	}
}
