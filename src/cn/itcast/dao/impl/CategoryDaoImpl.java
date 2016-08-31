package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.Category;
import cn.itcast.exception.DaoException;
import cn.itcast.util.DBCPUtil;
/*
create table category(
			id varchar(100) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
 */
public class CategoryDaoImpl implements CategoryDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void addCategory(Category c) {
		try {
			qr.update("insert into category (id,name,description) values(?,?,?)", c.getId(),c.getName(),c.getDescription());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Category> findAll() {
		try {
			return qr.query("select * from category", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Category findCatetoryById(String categoryId) {
		try {
			return qr.query("select * from category where id=?", new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
