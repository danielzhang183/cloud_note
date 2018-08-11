package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.UserService;
import cn.augmentum.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/user")
public class UserChangePwdController {

	@Resource
	UserService userService;
	@RequestMapping("/changePwd.do")
	@ResponseBody
	public NoteResult<Object> excute(String userId,String last_password,String new_password){
		System.out.println("Controller"+userId);
		NoteResult<Object> result=userService.updateUserPwd(userId, last_password, new_password);
		return result;
	}
	
}
