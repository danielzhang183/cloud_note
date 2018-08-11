package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.augmentum.cloud_note.entity.User;
import cn.augmentum.cloud_note.service.UserService;
import cn.augmentum.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	@Before
	public void init(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		service=ctx.getBean("userService",UserService.class);
	}
	@Test//用例1：预期结果
	public void test1(){
		NoteResult<User> result=service.checkLogin("Dylan", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test
	public void test2(){
		NoteResult<User> result=service.checkLogin("demo", "zz");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test
	public void test3(){
		NoteResult<User> result=service.checkLogin("demo", "111111");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test
	public void test4(){
		String name="George";
		String password="111111";
		String nick="rick";
		NoteResult<Object> result=service.addUser(name, nick, password);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
}
