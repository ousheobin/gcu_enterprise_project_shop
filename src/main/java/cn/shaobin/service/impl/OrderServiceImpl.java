package cn.shaobin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shaobin.dao.OrderDao;
import cn.shaobin.dao.OrderItemDao;
import cn.shaobin.dao.ShopCarDao;
import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.OrderEntity;
import cn.shaobin.entity.OrderItemEntity;
import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Resource
	ShopCarDao shopCarDao;
	
	@Resource
	OrderDao orderDao;
	
	@Resource
	OrderItemDao orderItemDao;

	@Override
	public void addShopCar(UserEntity user, ArticleEntity article, int amount) {
		ShopCarEntity shopCar = new ShopCarEntity();
		shopCar.setArticleId(article.getId());
		shopCar.setUserId(user.getId());
		shopCar.setBuyNum(amount);
		shopCarDao.addShopCar(shopCar);
	}

	@Override
	public List<ShopCarEntity> getShopCar(UserEntity user) {
		return shopCarDao.getShopCar(user);
	}

	@Override
	public void updateShopCar(ShopCarEntity shopCar) {
		shopCarDao.updateShopCar(shopCar);
	}

	@Override
	public void deleteShopCar(ShopCarEntity shopCar) {
		shopCarDao.deleteShopCar(shopCar);
	}

	@Override
	public ShopCarEntity getShopCarByUser(ArticleEntity article, UserEntity user) {
		return shopCarDao.getShopCarByUser(article.getId(), user.getId());
	}

	@Override
	public ShopCarEntity getShopCarById(int shopCarId) {
		return shopCarDao.getShopCarById(shopCarId);
	}

	@Override
	public void addOrder(OrderEntity order) {
		orderDao.addOrder(order);
		List<OrderItemEntity> items = order.getItems();
		if (items!= null && !items.isEmpty() ) {
			for(OrderItemEntity item : items) {
				item.setOrderId(order.getId());
				orderItemDao.addOrderItem(item);
			}
		}
		
	}

	@Override
	public List<OrderEntity> getOrderByUser(UserEntity user) {
		 List<OrderEntity> list =  orderDao.getOrderByUser(user);
		 return list;
	}

}
