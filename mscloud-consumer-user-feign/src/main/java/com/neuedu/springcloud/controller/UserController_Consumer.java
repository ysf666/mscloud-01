package com.neuedu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.springcloud.domain.User;
import com.neuedu.springcloud.service.UserClientService;



@RestController
public class UserController_Consumer {

		@Autowired
		private UserClientService service;

		@RequestMapping("/consumer/user/findAll")
		public List<User> findAll(){
			return service.findAll();
		}
		
		@RequestMapping("/consumer/user/findUserById/{id}")
		public User findUserById(@PathVariable("id") int id){

			return service.findUserById(id);
		}
		
		

}
