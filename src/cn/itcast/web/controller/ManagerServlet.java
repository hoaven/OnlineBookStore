package cn.itcast.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.util.Page;
import cn.itcast.util.WebUtil;

public class ManagerServlet extends HttpServlet {
	private BusinessService s = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addCategory".equals(operation)){
			addCategory(request,response);
		}
		if("showAllCatetory".equals(operation)){
			showAllCatetory(request,response);
		}
		if("showAllCatetoryUI".equals(operation)){
			showAllCatetoryUI(request,response);
		}
		if("addBook".equals(operation)){
			addBook(request,response);
		}
		if("showAllBook".equals(operation)){
			showAllBook(request,response);
		}
	}
	//后台查询所有图书分页
	private void showAllBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");   //第一次为空
		Page page = s.findPageRecords(pagenum);
		page.setUrl("/servlet/ManagerServlet?operation=showAllBook");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/listBooks.jsp").forward(request, response);
	}
	//添加书籍到数据库中
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String resultPath = "";
		String storePath = getServletContext().getRealPath("/files");
		try {
			Book book = new Book();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析请求
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item:items){
				if(item.isFormField()){
					//封装数据到JavaBean中
					String fieldName = item.getFieldName();//字段名，即javabean的属性名，除了UUID文件名
					String fieldValue = item.getString(request.getCharacterEncoding());
					BeanUtils.setProperty(book, fieldName, fieldValue);//book中除了UUID文件名，其他数据都有了
				}else{
					//处理文件上传
					InputStream in = item.getInputStream();
					String fileName = item.getName();//   c:\dsf\a.jpg
					fileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("\\")+1);//UUID+a.jpg 
					//设置存取的图片文件名
					book.setImage(fileName);
					
					OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
					byte b[] = new byte[1024];
					int len = -1;
					while((len=in.read(b))!=-1){
						out.write(b, 0, len);
					}
					out.close();
					in.close();
					item.delete();//删除临时文件
				}
			}
			s.addBook(book);
			//查询分类
			List<Category> cs = s.findAllCategory();
			request.setAttribute("cs", cs);
			resultPath = "/manager/addBook.jsp";
			request.setAttribute("message", "<script type='text/javascript'>alert('添加成功')</script>");
		} catch (Exception e) {
			e.printStackTrace();
			resultPath = "/message.jsp";
			request.setAttribute("message", "服务器忙");
		}
		request.getRequestDispatcher(resultPath).forward(request, response);
	}
	//查询所有分类，用于添加书籍时的显示
	private void showAllCatetoryUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = s.findAllCategory();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
	}
	private void showAllCatetory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = s.findAllCategory();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
	}
	//添加分类到数据库中
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Category c = WebUtil.fillBean(request, Category.class);
		s.addCategory(c);
		request.setAttribute("message", "<script type='text/javascript'>alert('添加成功');</script>");
		request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
