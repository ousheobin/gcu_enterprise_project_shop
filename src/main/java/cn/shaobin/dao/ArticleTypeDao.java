package cn.shaobin.dao;

import java.util.List;

import cn.shaobin.entity.ArticleTypeEntity;

public interface ArticleTypeDao {
	
	public List<ArticleTypeEntity> getAllMainTypes();
	
	public List<ArticleTypeEntity> getSubType(String currentType);
	
	public ArticleTypeEntity getTypeByCode(String code);

}
