package com.neuedu.springcloud.service;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.springcloud.domain.User;
import com.neuedu.springcloud.repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	// 注入UserRepository
	@Resource
	private UserRepository userRepository;
	
	//@Transactional(rollbackFor={Exception.class})
	@Transactional(noRollbackFor={Exception.class})
	public int insertUser(User user){
		int result = userRepository.insertUser(user);
		//事务测试
	    User u = null;
	    u.setId(3);
		return result;
	}
	
	public User selectByUsername(String username){
		return userRepository.selectByUsername(username);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public void insertGetKey(User user) {
		// 数据插入成功以后，Mybatis框架会将插入成功的数据主键存入到user对象中去
		userRepository.insertGetKey(user);
	}
	

	public int update(User user) {
		return userRepository.update(user);
	}
	
	public int delete(Integer id) {
		return userRepository.delete(id);
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(id);
	}
}