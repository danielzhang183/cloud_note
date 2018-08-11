package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.augmentum.cloud_note.dao.NoteDao;
import cn.augmentum.cloud_note.entity.Note;
import test.TestBase;

public class TestNoteDao extends TestBase{
	private NoteDao noteDao;
	@Before
	public void init(){
	   noteDao=super.getContext().getBean("noteDao",NoteDao.class);
	}

	@Test
	public void testNoteDao(){
		
		List<Map<String,String>> list=noteDao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Map<String,String> note:list){
			System.out.println(note.get("cn_note_id")+","+note.get("cn_note_title"));
		}
	}
	@Test
	public void testNoteload(){
		Note note=noteDao.findByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
		System.out.println(note.getCn_note_title());
		System.out.println(note.getCn_note_body());
		System.out.println(note.getCn_note_id());
	}
	@Test
	public void testUpdateNote(){
		Note note=noteDao.findByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
		note.setCn_note_body("123456");
		note.setCn_note_title("Josh Turner");
		System.out.println(note.getCn_note_title());
		System.out.println(note.getCn_note_body());
		System.out.println(note.getCn_note_id());
		int num=noteDao.updateNote(note);
		Note note1=noteDao.findByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
		System.out.println(num);
		System.out.println(note1.getCn_note_title());
		System.out.println(note1.getCn_note_body());
	}
	
	@Test
	public void testaddnote(){
		
	}
}
