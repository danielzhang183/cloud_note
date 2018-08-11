package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.ShareService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class AddShareController {

	@Resource
	ShareService shareService;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		System.out.println(noteId);
		NoteResult<Object> result=shareService.addShare(noteId);
		return result;
	}

}
