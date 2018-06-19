package cn.shaobin.service;

import cn.shaobin.entity.UserEntity;

public interface UserService {
	
	public void addUser(UserEntity user);
	
	public UserEntity getUserByName(String username);

}
