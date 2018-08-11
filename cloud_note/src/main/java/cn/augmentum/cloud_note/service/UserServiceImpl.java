 package cn.augmentum.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.augmentum.cloud_note.dao.UserDao;
import cn.augmentum.cloud_note.entity.User;
import cn.augmentum.cloud_note.util.NoteResult;
import cn.augmentum.cloud_note.util.NoteUtil;
@Service("userService")//ɨ�赽Spring����
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		//���ܽ������
		NoteResult<User> result=new NoteResult<User>();
		//������name��ѯ���ݿ�
		User user=userDao.findByName(name);
		//����û���
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		String md5Password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name, String nick, String password) {
		NoteResult<Object> result=new NoteResult<Object>();
		User user=null;
		user=userDao.findByName(name);
		//����û�
		if(user!=null){
			result.setStatus(1);
			result.setMsg("���û��Ѵ���");
			return result;
		}
		//����û�
		user=new User();
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_name(name);
		user.setCn_user_nick(nick);
		user.setCn_user_password(NoteUtil.md5(password));
		//�����û�����
		userDao.save(user);
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
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
			result.setMsg("�޸�����ɹ���");
		}else{
			result.setData(1);
			result.setMsg("ԭ������������");
		}
		return result;
	}

}
