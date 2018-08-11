package cn.augmentum.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.augmentum.cloud_note.dao.BookDao;
import cn.augmentum.cloud_note.entity.Book;
import cn.augmentum.cloud_note.util.NoteResult;
@Service("bookService") 
public class BookServiceImpl implements BookService {
	@Resource
	BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		List<Book> list=bookDao.findByUserId(userId);
		//�������ؽ��
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
	/*	if(list==null){
			result.setStatus(1);
			result.setMsg("�ʼǱ���ѯʧ��");
			return result;
		}*/
		result.setStatus(0);
		result.setMsg("�ʼǱ���ѯ�ɹ���");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String bookName, String userId) {
		Book book=new Book();
		//��������
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString().replace("-", "");
		book.setCn_notebook_id(id);
		//���ɴ���ʱ��
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		book.setCn_user_id(userId);
		
		NoteResult<Book> result=new NoteResult<Book>();
		List<Book> list=bookDao.findByUserId(userId);
		String str="";
		//int i=1;
/*		for(Book book1:list){
			
			if(bookName.equals(book1.getCn_notebook_name())){
				str=bookName+"("+i+")";
				book.setCn_notebook_name(str);
				i++;
			}else{
				book.setCn_notebook_name(bookName);
			}
		}*/
		for(int i=0;i<list.size();i++){
			
			if(bookName.equals(list.get(i).getCn_notebook_name())){
				str=bookName+"("+i+1+")";
				book.setCn_notebook_name(str);
				
			}else{
				book.setCn_notebook_name(bookName);
			}
		}
		
		bookDao.save(book);
		//System.out.println(book.getCn_notebook_name());
		result.setStatus(0);
		result.setMsg("�����ʼǱ��ɹ���");
		result.setData(book);
		return result;
	}
	
	//
	public NoteResult<Object> deleteBook(String bookId) {
		bookDao.delete(bookId);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("ɾ���ʼǱ��ɹ�");
		return result;
	}

}
