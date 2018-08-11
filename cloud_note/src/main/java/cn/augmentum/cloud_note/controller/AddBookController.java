package cn.augmentum.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.entity.Book;
import cn.augmentum.cloud_note.service.BookService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {

	@Resource
	BookService bookService;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Book> execute(String bookName,String userId){
		System.out.println(bookName+","+userId);
		NoteResult<Book> result=bookService.addBook(bookName, userId);
		return result;
	}
}
