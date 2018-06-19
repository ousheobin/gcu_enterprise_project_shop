package cn.shaobin.dao;

import java.util.List;

import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;

public interface ShopCarDao {
	
	public void addShopCar(ShopCarEntity shopCar);
	
	public void updateShopCar(ShopCarEntity shopCar);
	
	public void deleteShopCar(ShopCarEntity shopCar);
	
	public ShopCarEntity getShopCarByUser(int articleId,int userId);

	public ShopCarEntity getShopCarById(int shopCarId );
	
	public List<ShopCarEntity> getShopCar(UserEntity user);

}
