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
		result.setMsg("笔记查询成功！");
		result.setData(list);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=noteDao.findByNoteId(noteId);
		if(note==null){
			result.setStatus(1);
			result.setMsg("未找到数据！");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("笔记加载成功");
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
		//更新数据库记录
		int rows=noteDao.updateNote(note);
		//构建result
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存笔记成功");
		}else{
			result.setStatus(1);
			result.setMsg("保存笔记失败");
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
		//状态：1-normal  2-delete
		note.setCn_note_status_id("1");
		//类型：1-normal  2-favor  3-share
		note.setCn_note_type_id("1");
		noteDao.save(note);
		//构建result
		NoteResult<Note> result=new NoteResult<Note>();
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);
		return result;
	}
	
	public NoteResult<Object> deleteNote(String noteId) {
		noteDao.delete(noteId);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		return result;
	}

}
