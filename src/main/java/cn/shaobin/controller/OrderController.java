package cn.shaobin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.ArticleService;

@Controller
public class OrderController {
	
	@Resource
	ArticleService articleService;
	
	@RequestMapping(value="buy")
	@ResponseBody
	public void handleBuyEvent( String id, String buyNum ,HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("userEntity");
		if(user!=null) {
			try {
				int articleId = Integer.valueOf(id);
				int amount = Integer.valueOf(buyNum);
				ArticleEntity article = articleService.getArticleById(articleId);
			}catch(Exception ex) {
				
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
	}

}
