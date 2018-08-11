package cn.augmentum.cloud_note.service;

import java.util.List;

import cn.augmentum.cloud_note.entity.Book;
import cn.augmentum.cloud_note.util.NoteResult;


public interface BookService {
	public NoteResult<List<Book>> loadUserBooks(String userId);
	
	public NoteResult<Book> addBook(String bookName,String userId);

	public NoteResult<Object> deleteBook(String bookId);
}
