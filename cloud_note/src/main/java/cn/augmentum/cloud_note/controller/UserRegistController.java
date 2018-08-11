package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.UserService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserRegistController {

	@Resource
	UserService userService;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String name,String nick,String password){
		NoteResult<Object> result=userService.addUser(name, nick, password);
		return result;
	}
}
