package cn.shaobin.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.OrderItemDao;
import cn.shaobin.entity.OrderItemEntity;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addOrderItem(OrderItemEntity item) {
		sqlSessionTemplate.insert("OrderItemDao.addOrderItem",item);
	}

}
