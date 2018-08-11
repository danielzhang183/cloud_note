package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.service.BookService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class DeleteBookController {

	@Resource
	BookService bookService;
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult<Object> execute(String bookId){
		NoteResult<Object> result=bookService.deleteBook(bookId);
		return result;
	}
}
