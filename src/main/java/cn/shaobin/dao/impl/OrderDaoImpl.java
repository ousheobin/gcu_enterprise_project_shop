package cn.shaobin.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

}
