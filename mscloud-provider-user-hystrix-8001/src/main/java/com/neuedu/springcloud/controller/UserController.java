package com.neuedu.springcloud.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
	
	//一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	@RequestMapping("/user/findUserById/{id}")
	public User findUserById(@PathVariable("id") int id){
		User user = userService.findUserById(id);
		if (null == user) {
			throw new RuntimeException("该ID：" + id + "没有对应的用户信息");
		}
		return user;
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
	
	public User processHystrix_Get(@PathVariable("id") int id)
	{
		User user = new User();
		user.setId(id);
		user.setLoginName("该ID：" + id + "没有对应的用户");
		user.setDbSource("no this data in MySQL");
		return user;
	}

	
}
