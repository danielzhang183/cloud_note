package cn.augmentum.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.NoteService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {

	@Resource
	NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map<String,String>>> execute(String bookId){
		System.out.println(bookId);
		NoteResult<List<Map<String,String>>> result=noteService.loadBookNotes(bookId);
		return result;
	}
	
}
