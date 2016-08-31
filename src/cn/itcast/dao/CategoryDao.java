package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Category;

public interface CategoryDao {
	void addCategory(Category c);
	List<Category> findAll();
	Category findCatetoryById(String categoryId);
}
