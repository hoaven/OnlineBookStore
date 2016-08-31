package cn.itcast.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Category;
import cn.itcast.domain.Orders;
import cn.itcast.domain.OrdersItem;
import cn.itcast.domain.User;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.util.Page;
import cn.itcast.util.WebUtil;
//根据不同的请求调用不同的service层的业务方法，然后跳转到不同的页面
public class ClientServlet extends HttpServlet {
	private BusinessService s = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("showIndexCategory".equals(operation)){
			showIndexCategory(request,response);
		}
		if("showCategoryBooks".equals(operation)){
			showCategoryBooks(request,response);
		}
		if("buyBook".equals(operation)){
			buyBook(request,response);
		}
		if("regist".equals(operation)){
			regist(request,response);
		}
		if("login".equals(operation)){
			login(request,response);
		}
		if("genOrders".equals(operation)){
			genOrders(request,response);
		}
	}
	//生成订单：把订单和订单项的信息存到数据库中
	private void genOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//判断用户是否登录：1、没有登录，转向登录页面  2、已经登录
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登录！2秒后将自动转向登录页面。<meta http-equiv='Refresh' content='2;URL="+request.getContextPath()+"/client/login.jsp'>");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
			return;
		}
		//取出购物车：Cart  Map<String,CartItem>  把购物车填充到orders、ordersItem模型中，包括订单和订单项
		Cart cart = (Cart) session.getAttribute("cart");
		Orders orders = new Orders();
		orders.setNum(cart.getNum());
		orders.setPrice(cart.getPrice());
		List<OrdersItem> ordersItems = new ArrayList<OrdersItem>();
		for(Map.Entry<String,CartItem> item:cart.getItems().entrySet()){
			CartItem i = item.getValue();
			OrdersItem orderItem = new OrdersItem();
			orderItem.setNum(i.getNum());
			orderItem.setPrice(i.getPrice());
			orderItem.setBook(i.getBook());
			ordersItems.add(orderItem);
		}
		orders.setItems(ordersItems);
		s.addOrders(orders,user);
		request.setAttribute("message", "生成订单成功！");
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = s.login(username,password);
		if(user==null){
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath());
		}
		
	}
	//用户注册
	private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = WebUtil.fillBean(request, User.class);  //表单中没有特殊项，可以直接用WebUtil
		s.regist(user);
		request.setAttribute("message", "注册成功！");
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}
	//把购买的商品加入购物车
	private void buyBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取书的id
		String bookId = request.getParameter("bookId");
		//获取要购买的书
		Book book = s.findBookById(bookId);
		//从HttpSession中取出购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		//没有：创建购物车，并放到HttpSession中（购物车Cart）
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//把书放到购物车中
		cart.addBook(book);
		//提示购买成功
		request.setAttribute("message", "购买成功！");
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}
	//主页：按照分类进行分页书籍查询
	private void showCategoryBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		String categoryId = request.getParameter("categoryId");
		Page page = s.findPageRecords(pagenum,categoryId);
		page.setUrl("/servlet/ClientServlet?operation=showCategoryBooks&categoryId="+categoryId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/welcome.jsp").forward(request, response);
	}
	//查询所有分类，封装后，便于前端主页的显示
	//查询所有的书籍，还要分页
	private void showIndexCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = s.findAllCategory();
		request.getSession().setAttribute("cs", cs);
		//查询所有的书籍，还要分页
		String pagenum = request.getParameter("pagenum");
		Page page = s.findPageRecords(pagenum);
		page.setUrl("/servlet/ClientServlet?operation=showIndexCategory");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/welcome.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
