package cn.shaobin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shaobin.dao.ArticleDao;
import cn.shaobin.dao.ArticleTypeDao;
import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ArticleTypeEntity;
import cn.shaobin.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	ArticleTypeDao articleTypeDao;
	
	@Resource
	ArticleDao articleDao;

	@Override
	public List<ArticleTypeEntity> getAllMainTypes() {
		return articleTypeDao.getAllMainTypes();
	}

	@Override
	public List<ArticleTypeEntity> getSubType(String code) {
		return articleTypeDao.getSubType(code);
	}

	@Override
	public ArticleTypeEntity getTypeByCode(String code) {
		return articleTypeDao.getTypeByCode(code);
	}

	@Override
	public List<ArticleEntity> getArticles(String categoryId, String keyword) {
		return articleDao.getArticles(categoryId, keyword);
	}

	@Override
	public ArticleEntity getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

}
