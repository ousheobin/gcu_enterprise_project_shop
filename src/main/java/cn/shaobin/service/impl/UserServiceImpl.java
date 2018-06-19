package cn.shaobin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shaobin.dao.UserDao;
import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserDao userDao;

	@Override
	public void addUser(UserEntity user) {
		userDao.addUser(user);
	}

	@Override
	public UserEntity getUserByName(String username) {
		return userDao.getUserByName(username);
	}

}
