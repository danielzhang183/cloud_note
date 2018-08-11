package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.NoteService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {

	@Resource
	NoteService noteService;
	@RequestMapping("/updateNote.do")
	@ResponseBody
	public NoteResult<Object> ececute(String noteId,String title,String body){
		System.out.println(noteId+","+title+","+body);
		NoteResult<Object> result=noteService.updateNote(noteId,title,body);
		return result;
		
	}
}
