package cn.shaobin.service;

import java.util.List;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;

public interface OrderService {
	
	public void addShopCar(UserEntity user, ArticleEntity article, int amount);
	
	public void updateShopCar(ShopCarEntity shopCar);
	
	public void deleteShopCar(ShopCarEntity shopCar);
	
	public ShopCarEntity getShopCarByUser(ArticleEntity article, UserEntity user);
	
	public List<ShopCarEntity> getShopCar(UserEntity user);

}
