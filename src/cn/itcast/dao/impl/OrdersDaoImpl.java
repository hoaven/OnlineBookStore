package cn.itcast.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.dao.OrdersDao;
import cn.itcast.domain.Orders;
import cn.itcast.domain.OrdersItem;
import cn.itcast.domain.User;
import cn.itcast.exception.DaoException;
import cn.itcast.util.DBCPUtil;

public class OrdersDaoImpl implements OrdersDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	//保存订单的基本信息
	//订单中的订单项也要保存
	public void addOrders(Orders orders, User user) {
		try{
			qr.update("insert into orders (id,ordernum,num,price,user_id) values(?,?,?,?,?)", orders.getId(),orders.getOrdernum(),orders.getNum(),orders.getPrice(),user.getId());
			//订单中的订单项
			List<OrdersItem> items = orders.getItems();
			if(items!=null&&items.size()>0){
				String sql = "insert into ordersitem (id,num,price,orders_id,book_id) values(?,?,?,?,?)";
				Object pps[][] = new Object[items.size()][];
				for(int i=0;i<items.size();i++){
					OrdersItem item = items.get(i);
					pps[i] = new Object[]{item.getId(),item.getNum(),item.getPrice(),orders.getId(),item.getBook().getId()};
				}
				qr.batch(sql, pps);
			}
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}
