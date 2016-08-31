package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.Category;
import cn.itcast.domain.Orders;
import cn.itcast.domain.User;
import cn.itcast.util.Page;

public interface BusinessService {
	void addCategory(Category c);
	List<Category> findAllCategory();
	void addBook(Book book);
	/**
	 * ��̨��ѯͼ��ʹ��
	 * @param pagenum
	 * @return
	 */
	Page findPageRecords(String pagenum);
	Category findCatetoryById(String categoryId);
	//���շ����ѯ��ҳ����
	Page findPageRecords(String pagenum, String categoryId);
	Book findBookById(String bookId);
	void regist(User user);
	User login(String username, String password);
	//���ɶ���
	void addOrders(Orders orders, User user);
	
}
