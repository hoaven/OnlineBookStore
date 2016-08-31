package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserDao {

	void addUser(User user);

	User findUser(String username, String password);

}
