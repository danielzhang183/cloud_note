package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.service.NoteService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNoteController {

	@Resource
	NoteService noteService;
	
	@RequestMapping("load.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId){
		System.out.println(noteId);
		NoteResult<Note> result=noteService.loadNote(noteId);
		return result;
	}
}
