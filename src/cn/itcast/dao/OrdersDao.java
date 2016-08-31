package cn.itcast.dao;

import cn.itcast.domain.Orders;
import cn.itcast.domain.User;

public interface OrdersDao {
	//保存订单信息到数据库中
	void addOrders(Orders orders, User user);
	//
}
