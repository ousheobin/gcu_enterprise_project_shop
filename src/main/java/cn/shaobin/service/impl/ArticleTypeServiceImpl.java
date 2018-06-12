package cn.shaobin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shaobin.dao.ArticleTypeDao;
import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ArticleTypeEntity;
import cn.shaobin.service.ArticleTypeService;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {
	
	@Resource
	ArticleTypeDao articleTypeDao;

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

}
