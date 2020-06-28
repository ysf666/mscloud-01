package com.neuedu.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.neuedu.springcloud.domain.User;

import feign.hystrix.FallbackFactory;

@Component 
public class UserClientServiceFallbackFactory implements FallbackFactory<UserClientService>
{
	@Override
	public UserClientService create(Throwable throwable)
	{
		return new UserClientService() {

			@Override
			public List<User> findAll() {
				// TODO Auto-generated method stub			
				return null;
			}

			@Override
			public User findUserById(int id) {
				User user = new User();
				user.setId(id);
				user.setLoginName("该ID：" + id + "没有对应的用户");
				user.setDbSource("no this data in MySQL");
				return user;
			}
			
		};
	}
}
