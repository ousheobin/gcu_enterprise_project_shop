package cn.shaobin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.shaobin.dao.ArticleDao;
import cn.shaobin.entity.ArticleEntity;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ArticleEntity> getArticles(String categoryId, String keyword) {
		Map<String, String > parameters = new HashMap<String,String>();
		parameters.put("typeCode", "%"+categoryId+"%");
		if(keyword == null || keyword.isEmpty()) {
			parameters.put("keyWord", null);
		}else {
			parameters.put("keyWord", "%"+keyword+"%");
		}
		return sqlSessionTemplate.selectList("ArticleDao.getArticleByKeywordAndCate",parameters);
	}

}
