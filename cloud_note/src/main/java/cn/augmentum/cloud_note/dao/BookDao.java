package cn.augmentum.cloud_note.dao;

import java.util.List;

import cn.augmentum.cloud_note.entity.Book;

public interface BookDao {
	
	public List<Book> findByUserId(String userId);
	
	public void save(Book book);

	public void delete(String bookId);
}
