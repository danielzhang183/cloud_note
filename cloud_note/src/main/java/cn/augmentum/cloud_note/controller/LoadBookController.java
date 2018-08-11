package cn.augmentum.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.entity.Book;
import cn.augmentum.cloud_note.service.BookService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBookController {

	@Resource
	BookService bookService;
	
	@RequestMapping("/loadBookds.do")
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId){
		System.out.println(userId);
		NoteResult<List<Book>> result=bookService.loadUserBooks(userId);
		return result;
	}
}
