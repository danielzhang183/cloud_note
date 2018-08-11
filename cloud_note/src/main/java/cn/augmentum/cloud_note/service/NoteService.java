package cn.augmentum.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.util.NoteResult;

public interface NoteService {
	
	public NoteResult<List<Map<String,String>>> loadBookNotes(String bookId);
	
	public NoteResult<Note> loadNote(String noteId);
	
	public NoteResult<Object> updateNote(String noteId,String title,String body);
	
	public NoteResult<Note> addNote(String userId,String bookId,String title);
	
	public NoteResult<Object> deleteNote(String noteId);
}
