package cn.shaobin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.OrderDao;
import cn.shaobin.entity.OrderEntity;
import cn.shaobin.entity.UserEntity;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addOrder(OrderEntity order) {
		sqlSessionTemplate.insert("OrderDao.addOrder",order);
	}

	@Override
	public List<OrderEntity> getOrderByUser(UserEntity user) {
		return sqlSessionTemplate.selectList("OrderDao.getOrderByUser",user.getId());
	}

}
