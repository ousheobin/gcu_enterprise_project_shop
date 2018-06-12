package cn.shaobin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shaobin.entity.ArticleTypeEntity;
import cn.shaobin.service.ArticleTypeService;

@Controller
public class ListController {
	
	@Resource
	ArticleTypeService articleTypeService;
	
	@RequestMapping(value= {"","/","index"})
	public String getWelcomePage(HttpServletRequest request) {
		List<ArticleTypeEntity> allMainTypes = articleTypeService.getAllMainTypes();
		request.setAttribute("firstArticleTypes",allMainTypes );
		if(allMainTypes!=null && !allMainTypes.isEmpty()) {
			request.setAttribute("currentArticleType",allMainTypes.get(0) );
			request.setAttribute("allSecondArticleTypes", articleTypeService.getSubType(allMainTypes.get(0).getCode()));

		}
		return "list";
	}
	
	@RequestMapping(value= "item-{typeCode}-list.html")
	public String getSubItemList(@PathVariable()String typeCode , HttpServletRequest request) {
		ArticleTypeEntity type = articleTypeService.getTypeByCode(typeCode);
		if( type != null ) {
			if(typeCode!=null && typeCode.length()>4) {
				request.setAttribute("allSecondArticleTypes", articleTypeService.getSubType(typeCode.substring(0, 4)));
			}else {
				request.setAttribute("allSecondArticleTypes", articleTypeService.getSubType(type.getCode()));
			}
			request.setAttribute("firstArticleTypes",articleTypeService.getAllMainTypes() );
			request.setAttribute("currentArticleType",type );
			
		}
		return "list";
	}
	
	@RequestMapping(value="search.html")
	public String searchPage(String typecode , String keyword, HttpServletRequest request) {
		ArticleTypeEntity type = articleTypeService.getTypeByCode(typecode);
		if( type != null) {
			request.setAttribute("firstArticleTypes",articleTypeService.getAllMainTypes() );
			request.setAttribute("currentArticleType",type );
			if(typecode!=null && typecode.length()>4) {
				request.setAttribute("allSecondArticleTypes", articleTypeService.getSubType(typecode.substring(0, 4)));
			}else {
				request.setAttribute("allSecondArticleTypes", articleTypeService.getSubType(type.getCode()));
			}
		}
		return "list";
	}

}
