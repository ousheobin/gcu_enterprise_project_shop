package cn.shaobin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.ArticleTypeDao;
import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ArticleTypeEntity;

@Repository
public class ArticleTypeDaoImpl implements ArticleTypeDao {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ArticleTypeEntity> getAllMainTypes() {
		return sqlSessionTemplate.selectList("ArticleTypeDao.getAllMainType");
	}

	@Override
	public List<ArticleTypeEntity> getSubType(String currentType) {
		return sqlSessionTemplate.selectList("ArticleTypeDao.getSubType",currentType != null ? (currentType + "%") : "");
	}

	@Override
	public ArticleTypeEntity getTypeByCode(String code) {
		return sqlSessionTemplate.selectOne("ArticleTypeDao.getTypeByCode",code);
	}

}
