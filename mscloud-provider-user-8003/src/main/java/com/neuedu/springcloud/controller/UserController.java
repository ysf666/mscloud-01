package com.neuedu.springcloud.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.neuedu.springcloud.domain.User;
import com.neuedu.springcloud.service.UserServiceImpl;


@RestController
public class UserController {
	
	// 注入UserService
	@Resource
	private UserServiceImpl userService;
	
	@Autowired
	private DiscoveryClient client;
		
	@RequestMapping("/user/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("/user/findUserById/{id}")
	public User findUserById(@PathVariable("id") int id){

		return userService.findUserById(id);
	}
	
	@RequestMapping(value = "/user/discovery", method = RequestMethod.GET)
	public Object discovery()
	{
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("EUREKA-CLIENT-USER");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}
	
}
