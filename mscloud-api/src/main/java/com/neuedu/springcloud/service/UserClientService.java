package com.neuedu.springcloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neuedu.springcloud.domain.User;



@FeignClient(value = "eureka-client-user",fallbackFactory=UserClientServiceFallbackFactory.class)
public interface UserClientService
{	
	@RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
	public List<User> findAll();
	
	@RequestMapping(value = "/user/findUserById/{id}", method = RequestMethod.GET)
	public User findUserById(@PathVariable("id") int id);
}
