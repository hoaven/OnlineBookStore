package cn.itcast.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IdGenerator {
	public static String genPrimaryKey(){
		return UUID.randomUUID().toString();
	}
	//生成订单号===20130518+毫秒值
	public static String genOrdersNum(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = df.format(now)+System.currentTimeMillis();
		return str;
	}
}
