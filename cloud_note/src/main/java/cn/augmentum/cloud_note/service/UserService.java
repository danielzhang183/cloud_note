package cn.augmentum.cloud_note.service;

import cn.augmentum.cloud_note.entity.User;
import cn.augmentum.cloud_note.util.NoteResult;

public interface UserService {

	public NoteResult<User> checkLogin(String name,String password);
	
	public NoteResult<Object> addUser(String name,String nick,String password);
	
	public NoteResult<Object> updateUserPwd(String userId, String last_password,String new_password);
}
