package cn.itcast.dao;

import cn.itcast.domain.Orders;
import cn.itcast.domain.User;

public interface OrdersDao {
	//���涩����Ϣ�����ݿ���
	void addOrders(Orders orders, User user);
	//
}
