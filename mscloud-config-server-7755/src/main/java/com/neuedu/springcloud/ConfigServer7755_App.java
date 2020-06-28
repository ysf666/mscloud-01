package com.neuedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class ConfigServer7755_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(ConfigServer7755_App.class, args);
	}
}
