package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Book;

public interface BookDao {

	void addBook(Book book);
	int getTotalRecord();//查询所有的记录条数
	List<Book> findPageBooks(int startIndex,int pagesize);
	int getTotalRecord(String categoryId);
	List findPageBooks(int startindex, int pagesize, String categoryId);
	Book findBookById(String bookId);

}
