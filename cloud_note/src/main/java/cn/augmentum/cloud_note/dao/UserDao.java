package cn.augmentum.cloud_note.dao;

import cn.augmentum.cloud_note.entity.User;

public interface UserDao {
	//��¼����
	public User findByName(String name);
	
	public User findById(String userId);
	//ע�᷽��
	public void save(User user);
	//�޸�����
	public void update(User user);
}
