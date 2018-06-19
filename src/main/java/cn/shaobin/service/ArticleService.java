package cn.shaobin.service;

import java.util.List;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ArticleTypeEntity;

public interface ArticleService {
	
	public List<ArticleTypeEntity> getAllMainTypes();
	
	public List<ArticleTypeEntity> getSubType(String code);
	
	public ArticleTypeEntity getTypeByCode(String code);

	public List<ArticleEntity> getArticles(String categoryId, String keyword);
	
	public ArticleEntity getArticleById(int id);
	
}
