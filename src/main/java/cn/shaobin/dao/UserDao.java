package cn.shaobin.dao;

import cn.shaobin.entity.UserEntity;

public interface UserDao {
	
	public void addUser(UserEntity user);
	
	public UserEntity getUserByName(String username);

}
