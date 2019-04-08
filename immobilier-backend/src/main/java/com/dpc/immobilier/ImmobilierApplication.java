package com.dpc.immobilier;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dpc.domain.Mail;
import com.dpc.service.EmailService;
import com.dpc.service.StorageService;




@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.dpc.repository"})
@EntityScan({ "com.ekino.oss.springframework.data.postgresql","com.dpc.domain"})
@ComponentScan(basePackages = { "com.dpc"})


public class ImmobilierApplication implements CommandLineRunner {

	@Resource
	private StorageService storageService;
	 @Autowired
	    private EmailService emailService;
		
	 public static void main(String[] args) {
			SpringApplication.run(ImmobilierApplication.class, args);
		}
	 
	@Override
	public void run(String... arg) throws Exception {


	// storageService.init();
		
	     
	 
		
	}
	

	
}
