package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.entity.User;
import cn.augmentum.cloud_note.service.UserService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")//ƥ������·��
public class UserLoginController {
	@Resource
	public UserService userService;
	@RequestMapping("/login.do")//ƥ������
	@ResponseBody  //JSON���
	public NoteResult<User> execute(String name,String password){
		//����UserService�����¼����
		System.out.println(name+","+password);
		NoteResult<User> result=userService.checkLogin(name, password);
		return result;
	}
}
