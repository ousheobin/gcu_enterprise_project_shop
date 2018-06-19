package cn.shaobin.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.ShopCarEntity;
import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.ArticleService;
import cn.shaobin.service.OrderService;

@Controller
public class OrderController {
	
	@Resource
	ArticleService articleService;
	
	@Resource
	OrderService orderService;
	
	@RequestMapping(value="buy")
	@ResponseBody
	public void handleBuyEvent( String id, String buyNum ,HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		if(user!=null) {
			try {
				int articleId = Integer.valueOf(id);
				int amount = Integer.valueOf(buyNum);
				ArticleEntity article = articleService.getArticleById(articleId);
				ShopCarEntity item = orderService.getShopCarByUser(article, user);
				if(item != null) {
					item.setBuyNum(item.getBuyNum() + amount);
					orderService.updateShopCar(item);
				}else {
					orderService.addShopCar(user, article, amount);
				}
				response.sendRedirect(request.getContextPath()+"/member/shop-car.html");
			}catch(Exception ex) {
				
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
	}
	
	@RequestMapping(value="member/shop-car.html")
	public String getShopCar(HttpServletRequest request ) {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		List<ShopCarEntity> shopCar = orderService.getShopCar(user);
		int sum = 0;
		double prize = 0;
		for(ShopCarEntity item : shopCar ) {
			sum += item.getBuyNum();
			prize += item.getBuyNum() * (item.getArticle().getDiscount() *item.getArticle().getPrice() );
		}
		request.setAttribute("shopCars",shopCar);
		request.setAttribute("prize",prize);
		request.setAttribute("sum",sum);
		return "shopCar";
	}
	
	@RequestMapping(value="member/orders.html")
	public String getOrders(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		return "orderList";
	}
	
	@RequestMapping(value="member/shop_car_remove")
	public void handleShopCarRemove(String id, HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		try {
			int articleId = Integer.valueOf(id);
			ArticleEntity article = articleService.getArticleById(articleId);
			if(article!=null && user != null ) {
				ShopCarEntity item = orderService.getShopCarByUser(article, user);
				if(item != null) {
					orderService.deleteShopCar(item);
				}
			}
		}catch(Exception ex) {
			
		}
		response.sendRedirect(request.getContextPath()+"/member/shop-car.html");
		
	}
	
	@RequestMapping(value="member/shop_car_update")
	public void handleShopCarUpdate(String id, String buyNum,HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		try {
			int articleId = Integer.valueOf(id);
			int amount = Integer.valueOf(buyNum);
			ArticleEntity article = articleService.getArticleById(articleId);
			if(article!=null && user != null ) {
				ShopCarEntity item = orderService.getShopCarByUser(article, user);
				if(item != null) {
					item.setBuyNum(amount);
					orderService.updateShopCar(item);
				}
			}
		}catch(Exception ex) {
			
		}
		response.sendRedirect(request.getContextPath()+"/member/shop-car.html");
	}

}
