package test.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.augmentum.cloud_note.dao.BookDao;
import cn.augmentum.cloud_note.entity.Book;

public class TestBookDao {

	
	String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	@Test
	public void testDao(){
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		List<Book> list=dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for(Book book:list){
			System.out.println(book.getCn_notebook_id()+","+book.getCn_notebook_name());
			}
	}
	
	@Test
	public void testInsertDao(){
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		Book book=new Book();
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		book.setCn_notebook_id(id.replace("-", ""));
		book.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		book.setCn_notebook_name("Dead Dirary");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		System.out.println(book);
		dao.save(book);
		
	}
	
}

