package cn.shaobin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shaobin.entity.ArticleTypeEntity;
import cn.shaobin.service.ArticleService;

@Controller
public class ListController {
	
	@Resource
	ArticleService articleService;
	
	@RequestMapping(value= {"","/","index"})
	public String getWelcomePage(HttpServletRequest request) {
		List<ArticleTypeEntity> allMainTypes = articleService.getAllMainTypes();
		request.setAttribute("firstArticleTypes",allMainTypes );
		if(allMainTypes!=null && !allMainTypes.isEmpty()) {
			request.setAttribute("currentArticleType",allMainTypes.get(0) );
			request.setAttribute("allSecondArticleTypes", articleService.getSubType(allMainTypes.get(0).getCode()));
			request.setAttribute("articles", articleService.getArticles(allMainTypes.get(0).getCode(), null));
		}
		return "list";
	}
	
	@RequestMapping(value= "item-{typeCode}-list.html")
	public String getSubItemList(@PathVariable()String typeCode , HttpServletRequest request) {
		ArticleTypeEntity type = articleService.getTypeByCode(typeCode);
		if( type != null ) {
			if(typeCode!=null && typeCode.length()>4) {
				request.setAttribute("allSecondArticleTypes", articleService.getSubType(typeCode.substring(0, 4)));
			}else {
				request.setAttribute("allSecondArticleTypes", articleService.getSubType(type.getCode()));
			}
			request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
			request.setAttribute("currentArticleType",type );
			request.setAttribute("articles", articleService.getArticles(typeCode, null));
		}
		return "list";
	}
	
	@RequestMapping(value="search.html")
	public String searchPage(String typecode , String keyword, HttpServletRequest request) {
		ArticleTypeEntity type = articleService.getTypeByCode(typecode);
		if( type != null) {
			request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
			request.setAttribute("currentArticleType",type );
			if(typecode!=null && typecode.length()>4) {
				request.setAttribute("allSecondArticleTypes", articleService.getSubType(typecode.substring(0, 4)));
			}else {
				request.setAttribute("allSecondArticleTypes", articleService.getSubType(type.getCode()));
			}
			request.setAttribute("articles", articleService.getArticles(typecode, keyword));
		}
		return "list";
	}

}
