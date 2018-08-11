package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.augmentum.cloud_note.entity.Book;
import cn.augmentum.cloud_note.service.BookService;
import cn.augmentum.cloud_note.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase{

	private BookService bookService;
	@Before
	public void init(){
		bookService=super.getContext().getBean("bookService",BookService.class);
	}
	@Test
	public void testloadUserBooks(){
		NoteResult<List<Book>> result=bookService.loadUserBooks("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		for(Book book:result.getData()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test 
	public void testaddBook(){
		NoteResult<Book> result=bookService.addBook("Dead Dirary","48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
}
