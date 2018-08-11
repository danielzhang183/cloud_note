package cn.augmentum.cloud_note.dao;

import cn.augmentum.cloud_note.entity.User;

public interface UserDao {
	//登录方法
	public User findByName(String name);
	
	public User findById(String userId);
	//注册方法
	public void save(User user);
	//修改密码
	public void update(User user);
}
