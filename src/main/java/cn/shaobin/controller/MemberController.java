package cn.shaobin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shaobin.service.ArticleService;

@Controller
public class MemberController {
	
	@Resource
	ArticleService articleService;
	
	@RequestMapping(value="login.html")
	public String getLoginPage(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		return "login";
	}
	
	@RequestMapping(value="register.html")
	public String getRegisiterPage(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		return "register";
	}

}
