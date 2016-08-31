package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.CategoryDao;
import cn.itcast.dao.OrdersDao;
import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.dao.impl.CategoryDaoImpl;
import cn.itcast.dao.impl.OrdersDaoImpl;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.Category;
import cn.itcast.domain.Orders;
import cn.itcast.domain.OrdersItem;
import cn.itcast.domain.User;
import cn.itcast.service.BusinessService;
import cn.itcast.util.IdGenerator;
import cn.itcast.util.Page;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cDao = new CategoryDaoImpl();
	private BookDao bDao = new BookDaoImpl();
	private UserDao uDao = new UserDaoImpl();
	private OrdersDao oDao = new OrdersDaoImpl();
	public void addCategory(Category c) {
		c.setId(IdGenerator.genPrimaryKey());
		cDao.addCategory(c);
	}

	public List<Category> findAllCategory() {
		return cDao.findAll();
	}

	public void addBook(Book book) {
		book.setId(IdGenerator.genPrimaryKey());
		bDao.addBook(book);
	}

	public Page findPageRecords(String pagenum) {
		int num = 1;//默认页码
		if(pagenum!=null&&!"".equals(pagenum.trim())){
			num = Integer.parseInt(pagenum);
		}
		int totalrecords = bDao.getTotalRecord();
		Page page = new Page(num, totalrecords);
		List records = bDao.findPageBooks(page.getStartindex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	public Category findCatetoryById(String categoryId) {
		return cDao.findCatetoryById(categoryId);
	}

	public Page findPageRecords(String pagenum, String categoryId) {
		int num = 1;//默认页码
		if(pagenum!=null&&!"".equals(pagenum.trim())){
			num = Integer.parseInt(pagenum);
		}
		int totalrecords = bDao.getTotalRecord(categoryId);
		Page page = new Page(num, totalrecords);
		List records = bDao.findPageBooks(page.getStartindex(), page.getPagesize(),categoryId);
		page.setRecords(records);
		return page;
	}

	public Book findBookById(String bookId) {
		return bDao.findBookById(bookId);
	}

	public void regist(User user) {
		user.setId(IdGenerator.genPrimaryKey());
		uDao.addUser(user);
	}

	public User login(String username, String password) {
		return uDao.findUser(username,password);
	}

	public void addOrders(Orders orders, User user) {
		orders.setId(IdGenerator.genPrimaryKey());
		orders.setOrdernum(IdGenerator.genOrdersNum());
		//给购物项添加id
		List<OrdersItem> items = orders.getItems();
		for(OrdersItem item:items)
			item.setId(IdGenerator.genPrimaryKey());
		oDao.addOrders(orders,user);
	}

}
