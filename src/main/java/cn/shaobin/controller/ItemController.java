package cn.shaobin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shaobin.service.ArticleService;

@Controller
public class ItemController {
	
	@Resource
	ArticleService articleService;
	
	@RequestMapping(value="items/{itemId}.html")
	public String getItem(@PathVariable()String itemId,HttpServletRequest request) {
		int id = 0;
		try{
			id = Integer.parseInt(itemId);
		}catch(Exception ex) {
			
		}
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		request.setAttribute("article", articleService.getArticleById(id));
		return "item";
	}

}
