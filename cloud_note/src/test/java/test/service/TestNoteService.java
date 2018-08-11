package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.service.NoteService;
import cn.augmentum.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{

	private NoteService noteService;
	@Before
	public void init(){
		noteService=super.getContext().getBean("noteService",NoteService.class);
	}
	@Test
	public void testLoadBookNotes(){
		NoteResult<List<Map<String,String>>> result=noteService.loadBookNotes("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		for(Map<String,String> map:result.getData()){
			System.out.println(map.get("cn_note_id")+","+map.get("cn_note_title"));
		}
	}
	@Test
	public void testLoadNote(){
		NoteResult<Note> result=noteService.loadNote("07305c91-d9fa-420d-af09-c3ff209608ff");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		//Map<String,String> map=result.getData();
		//System.out.println(map.get("cn_note_title")+","+map.get("cn_note_body"));
		
	}
	@Test
	public void testUpdateNote(){
		String noteId="07305c91-d9fa-420d-af09-c3ff209608ff";
		String title="Marrron 5";
		String body="Girls like you";
		NoteResult<Object> result=noteService.updateNote(noteId, title, body);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
}
