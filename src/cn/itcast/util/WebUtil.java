package cn.itcast.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtil {    //将request请求表单中数据封装成对象
	public static <T>T fillBean(HttpServletRequest request,Class<T> clazz){
		try {
			T t = clazz.newInstance();// 调用默认构造方法创建对象
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
}
