package cn.shaobin.dao;

import java.util.List;

import cn.shaobin.entity.ArticleEntity;

public interface ArticleDao {
	
	public List<ArticleEntity> getArticles(String categoryId, String keyword);

}
