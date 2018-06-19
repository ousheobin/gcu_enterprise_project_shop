package cn.shaobin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.ShopCarDao;
import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;

@Repository
public class ShopCarDaoImpl implements ShopCarDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addShopCar(ShopCarEntity shopCar) {
		sqlSessionTemplate.insert("ShopCarDao.addShopCarItem",shopCar);
	}

	@Override
	public List<ShopCarEntity> getShopCar(UserEntity user) {
		return sqlSessionTemplate.selectList("ShopCarDao.getShopCarItemByUser",user);
	}

	@Override
	public void updateShopCar(ShopCarEntity shopCar) {
		sqlSessionTemplate.update("ShopCarDao.updateShopCar",shopCar);
	}

	@Override
	public void deleteShopCar(ShopCarEntity shopCar) {
		sqlSessionTemplate.update("ShopCarDao.deleteShopCar",shopCar);
	}

	@Override
	public ShopCarEntity getShopCarByUser(int articleId, int userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("articleId", articleId);
		param.put("userId", userId);
		return sqlSessionTemplate.selectOne("ShopCarDao.getSingleShopCarItem",param);
	}

}
