package cn.shaobin.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.ShopCarDao;

@Repository
public class ShopCarDaoImpl implements ShopCarDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

}
