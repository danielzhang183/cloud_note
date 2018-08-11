package cn.augmentum.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.augmentum.cloud_note.entity.Note;

public interface NoteDao {
	
	public List<Map<String,String>> findByBookId(String bookId);
	
	public Note findByNoteId(String noteId);
	
	public int updateNote(Note note);
	
	public void save(Note note);
	
	public void delete(String noteId);
	
}
