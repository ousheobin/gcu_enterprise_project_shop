package cn.shaobin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shaobin.dao.ShopCarDao;
import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Resource
	ShopCarDao shopCarDao;

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

}
