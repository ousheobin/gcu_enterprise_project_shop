package cn.shaobin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shaobin.entity.ArticleEntity;
import cn.shaobin.entity.OrderEntity;
import cn.shaobin.entity.OrderItemEntity;
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
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		request.setAttribute("orders", orderService.getOrderByUser(user));
		 System.out.println(orderService.getOrderByUser(user).get(0).getItems());
		return "orderList";
	}
	
	@RequestMapping(value="comfirm-order.html")
	public String comfirmOrderPage(String orderInfo,HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		List<ShopCarEntity> items = new ArrayList<ShopCarEntity>();
		double prize = 0;
		String[] orderInfoArray = orderInfo.split(",");
		if(orderInfoArray!=null && orderInfoArray.length > 0 ) {
			for(String order : orderInfoArray ) {
				try {
					int orderId = Integer.valueOf(order);
					ShopCarEntity item = orderService.getShopCarById(orderId);
					if(item!=null && item.getUserId() == user.getId()) {
						items.add(item);
						prize += item.getBuyNum() * (item.getArticle().getDiscount() *item.getArticle().getPrice() );
					}
				}catch(Exception ex) {
					
				}
			}
			request.setAttribute("totalMoney",prize);
			request.setAttribute("orderInfo",orderInfo);
			request.setAttribute("shopCars", items);
		}else{
			response.sendRedirect(request.getContextPath()+"/member/shop-car.html");
		}
		return "order";
	}
	
	@RequestMapping(value="confirm_order")
	@ResponseBody
	public void handleOrderConfirm(String[] orderInfo,String total,HttpServletRequest request , HttpServletResponse response) throws IOException {
		UserEntity user = (UserEntity) request.getSession().getAttribute("session_user");
		if(orderInfo!=null && orderInfo.length > 0 ) {
			OrderEntity order = new OrderEntity();
			order.setAmount(Double.parseDouble(total));
			order.setCreateDate(new Date());
			order.setOrderCode(Long.toString(System.currentTimeMillis()));
			order.setSendDate(new Date());
			order.setStatus("1");
			order.setUserId(user.getId());
			List<OrderItemEntity> orderItems = new ArrayList<OrderItemEntity>();
			for(String orderInfoId : orderInfo ) {
				try {
					int orderId = Integer.valueOf(orderInfoId);
					ShopCarEntity item = orderService.getShopCarById(orderId);
					if(item!=null && item.getUserId() == user.getId()) {
						OrderItemEntity orderItem = new OrderItemEntity();
						orderItem.setArticleId(item.getArticleId());
						orderItem.setOrderNum(item.getArticleId());
						orderService.deleteShopCar(item);
						orderItems.add(orderItem);
					}
				}catch(Exception ex) {
					
				}
			}
			order.setItems(orderItems);
			orderService.addOrder(order);
		}
		response.sendRedirect(request.getContextPath()+"/member/orders.html");	
	}
	
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
