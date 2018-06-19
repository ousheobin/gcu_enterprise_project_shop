package cn.shaobin.dao;

import java.util.List;

import cn.shaobin.entity.OrderEntity;
import cn.shaobin.entity.UserEntity;

public interface OrderDao {

	public void addOrder(OrderEntity order);
	
	public List<OrderEntity> getOrderByUser(UserEntity user);
	
}
