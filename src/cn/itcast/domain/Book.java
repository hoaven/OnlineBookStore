package cn.itcast.domain;

import java.io.Serializable;
/**
 * create table book(
			id varchar(100) primary key,
			name varchar(100) not null,
			author varchar(100),
			price float(8,2),
			image varchar(100),
			description varchar(255),
			category_id varchar(100),
			constraint category_id_fk foreign key(category_id) references category(id)
		);
 * @author hhc
 *
 */
public class Book implements Serializable {
	private String id;
	private String name;
	private String author;
	private float price;
	private String image;//UUIDÎÄ¼þÃû
	private String description;
	private String category_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String categoryId) {
		category_id = categoryId;
	}
	@Override
	public String toString() {
		return "Book [author=" + author + ", category_id=" + category_id
				+ ", description=" + description + ", id=" + id + ", image="
				+ image + ", name=" + name + ", price=" + price + "]";
	}
	
}
