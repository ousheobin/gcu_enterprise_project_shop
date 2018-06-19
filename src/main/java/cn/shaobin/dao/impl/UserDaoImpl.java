package cn.shaobin.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.UserDao;
import cn.shaobin.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addUser(UserEntity user) {
		sqlSessionTemplate.insert("UserDao.addUser",user);
	}

	@Override
	public UserEntity getUserByName(String username) {
		return sqlSessionTemplate.selectOne("UserDao.getUserByName",username);
	}

}
