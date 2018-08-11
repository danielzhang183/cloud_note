package cn.augmentum.cloud_note.service;

import java.util.List;

import cn.augmentum.cloud_note.entity.Share;
import cn.augmentum.cloud_note.util.NoteResult;

public interface ShareService {

	public NoteResult<Object> addShare(String noteId);
	
	public NoteResult<List<Share>> searchNote(String keyword,int page);
}
