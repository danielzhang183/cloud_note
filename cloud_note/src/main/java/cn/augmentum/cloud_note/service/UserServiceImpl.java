 package cn.augmentum.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.augmentum.cloud_note.dao.UserDao;
import cn.augmentum.cloud_note.entity.User;
import cn.augmentum.cloud_note.util.NoteResult;
import cn.augmentum.cloud_note.util.NoteUtil;
@Service("userService")//扫描到Spring容器
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		//接受结果数据
		NoteResult<User> result=new NoteResult<User>();
		//按参数name查询数据库
		User user=userDao.findByName(name);
		//检测用户名
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		String md5Password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name, String nick, String password) {
		NoteResult<Object> result=new NoteResult<Object>();
		User user=null;
		user=userDao.findByName(name);
		//检查用户
		if(user!=null){
			result.setStatus(1);
			result.setMsg("该用户已存在");
			return result;
		}
		//添加用户
		user=new User();
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_name(name);
		user.setCn_user_nick(nick);
		user.setCn_user_password(NoteUtil.md5(password));
		//插入用户数据
		userDao.save(user);
		//构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	public NoteResult<Object> updateUserPwd(String userId, String last_password,String new_password) {
		NoteResult<Object> result=new NoteResult<Object>();
		System.out.println("Service"+userId);
		User user=userDao.findById(userId);
		System.out.println(user.getCn_user_password());
		if(user.getCn_user_password()
				.equals(NoteUtil.md5(last_password))){
			user.setCn_user_password(NoteUtil.md5(new_password));
			userDao.update(user);
			result.setStatus(0);
			result.setMsg("修改密码成功！");
		}else{
			result.setData(1);
			result.setMsg("原密码输入有误！");
		}
		return result;
	}

}
