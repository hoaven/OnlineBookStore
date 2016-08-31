package cn.itcast.web.functions;

import cn.itcast.domain.Category;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

public class MyFunction {
	public static String getCategoryName(String categoryId){
		BusinessService s = new BusinessServiceImpl();
		Category c = s.findCatetoryById(categoryId);
		if(c!=null)
			return c.getName();
		return "";
	}
}
