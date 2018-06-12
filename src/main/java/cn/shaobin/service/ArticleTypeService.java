package cn.shaobin.service;

import java.util.List;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ArticleTypeEntity;

public interface ArticleTypeService {
	
	public List<ArticleTypeEntity> getAllMainTypes();
	
	public List<ArticleTypeEntity> getSubType(String code);
	
	public ArticleTypeEntity getTypeByCode(String code);

}
