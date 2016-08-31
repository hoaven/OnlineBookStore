package cn.itcast.domain;

import java.io.Serializable;

public class CartItem implements Serializable {
	private Book book;
	private int num;  //书的数量
	private float price;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return num*book.getPrice();
	}
//	public void setPrice(float price) {
//		this.price = price;
//	}
	
}
