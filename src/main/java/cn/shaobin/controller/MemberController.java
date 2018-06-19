package cn.shaobin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shaobin.entity.UserEntity;
import cn.shaobin.service.ArticleService;
import cn.shaobin.service.UserService;
import cn.shaobin.util.VericodeUtil;

@Controller
public class MemberController {
	
	@Resource
	ArticleService articleService;
	
	@Resource
	UserService userService;
	
	@RequestMapping(value="login.html")
	public String getLoginPage(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		request.setAttribute("message", request.getSession().getAttribute("errorMsg"));
		request.getSession().removeAttribute("errorMsg");
		return "login";
	}
	
	@RequestMapping(value="register.html")
	public String getRegisiterPage(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		request.setAttribute("message", request.getSession().getAttribute("errorMsg"));
		request.getSession().removeAttribute("errorMsg");
		return "register";
	}
	
	@RequestMapping(value="member/shop-car.html")
	public String getShopCar(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		return "shopCar";
	}
	
	@RequestMapping(value="member/orders.html")
	public String getOrders(HttpServletRequest request ) {
		request.setAttribute("firstArticleTypes",articleService.getAllMainTypes() );
		return "orderList";
	}
	
	@RequestMapping(value="regisiter_vericode")
	@ResponseBody
	public void getVericode(HttpServletRequest request,HttpServletResponse response) {
		VericodeUtil.getRandcode(request, response);
	}
	
	@RequestMapping(value="do_regisiter")
	@ResponseBody
	public void handleRegisiter(String loginName, String passWord, String name, String sex, 
			String address,String phone,String authcode,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String code = (String) request.getSession().getAttribute("verifyCode");
		 
		if(code!=null&&authcode!=null&&code.equals(authcode)) {
			 UserEntity user = new UserEntity();
			 user.setActive("已激活");
			 user.setAddress(address);
			 user.setCreateDate(new Date());
			 user.setDisabled(1);
			 user.setEmail(loginName);
			 user.setName(name);
			 user.setLoginName(loginName);
			 user.setPassword(Base64.encodeBase64String(DigestUtils.sha(DigestUtils.md5(passWord))).trim());
			 user.setPhone(phone);
			 user.setRole(1);
			 userService.addUser(user);
			 request.getSession().removeAttribute("verifyCode");
			 request.getSession().removeAttribute("loginName");
			 request.getSession().removeAttribute("phone");
			 request.getSession().removeAttribute("address");
			 request.getSession().removeAttribute("name");
			 response.sendRedirect(request.getContextPath()+"/login.html");
		}else {
			request.getSession().setAttribute("loginName", loginName);
			request.getSession().setAttribute("phone", phone);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("address", address);
			request.getSession().setAttribute("errorMsg", "验证码有误，请重试");
			response.sendRedirect(request.getContextPath()+"/register.html");
		}
	}
	
	@RequestMapping(value="do_login")
	@ResponseBody
	public void handleLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserEntity user = userService.getUserByName(username);
		if( user != null ) {
			if(user.getPassword().equals(Base64.encodeBase64String(DigestUtils.sha(DigestUtils.md5(password))).trim())) {
				request.getSession().setAttribute("session_user", user);
				response.sendRedirect(request.getContextPath());
			}else {
				request.getSession().setAttribute("errorMsg", "用户名或密码有误");
				response.sendRedirect(request.getContextPath()+"/login.html");
			}
		}else {
			request.getSession().setAttribute("errorMsg", "用户名或密码有误");
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
	}
	
	@RequestMapping(value="logout")
	@ResponseBody
	public void handleLogout(String username,String password,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/login.html");
	}
}
