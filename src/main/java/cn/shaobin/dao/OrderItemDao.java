package cn.shaobin.dao;

import java.util.List;

import cn.shaobin.entity.OrderItemEntity;

public interface OrderItemDao {
	
	public void addOrderItem(OrderItemEntity item);
	
	public List<OrderItemEntity> getOrderItemByOrder(int orderId);

}
