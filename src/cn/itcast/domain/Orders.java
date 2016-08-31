package cn.itcast.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//订单信息：对应的就是购物车的基本信息
public class Orders implements Serializable{
	private String id;
	private String ordernum;//订单号。notnull  unique
	private int num;//数量
	private float price;//付款金额
	
	private List<OrdersItem> items = new ArrayList<OrdersItem>();//订单中的订单项
	
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrdersItem> getItems() {
		return items;
	}

	public void setItems(List<OrdersItem> items) {
		this.items = items;
	}
	
	
}
