package cn.augmentum.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.augmentum.cloud_note.dao.NoteDao;
import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.util.NoteResult;
import cn.augmentum.cloud_note.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	NoteDao noteDao;
	public NoteResult<List<Map<String, String>>> loadBookNotes(String bookId) {
		NoteResult<List<Map<String, String>>> result=new NoteResult<List<Map<String,String>>>();
		List<Map<String, String>> list=noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("�ʼǲ�ѯ�ɹ���");
		result.setData(list);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=noteDao.findByNoteId(noteId);
		if(note==null){
			result.setStatus(1);
			result.setMsg("δ�ҵ����ݣ�");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("�ʼǼ��سɹ�");
			result.setData(note);
			return result;
		}
	}
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//�������ݿ��¼
		int rows=noteDao.updateNote(note);
		//����result
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
		}else{
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ��");
		}
		return result;
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_body("");
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		//״̬��1-normal  2-delete
		note.setCn_note_status_id("1");
		//���ͣ�1-normal  2-favor  3-share
		note.setCn_note_type_id("1");
		noteDao.save(note);
		//����result
		NoteResult<Note> result=new NoteResult<Note>();
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(note);
		return result;
	}
	
	public NoteResult<Object> deleteNote(String noteId) {
		noteDao.delete(noteId);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ�");
		return result;
	}

}
