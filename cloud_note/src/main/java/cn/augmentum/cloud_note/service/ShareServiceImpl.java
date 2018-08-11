package cn.augmentum.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.augmentum.cloud_note.dao.NoteDao;
import cn.augmentum.cloud_note.dao.ShareDao;
import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.entity.Share;
import cn.augmentum.cloud_note.util.NoteResult;
import cn.augmentum.cloud_note.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService {

	@Resource
	private NoteDao noteDao;
	@Resource
	ShareDao shareDao;
	
	public NoteResult<Object> addShare(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
//		System.out.println(note);
	    System.out.println("addShare,"+noteId);
		Share share=new Share();
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_note_id(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		//System.out.println(share);
		//保存share
		shareDao.save(share);
		//构造result
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("笔记分享成功！");
		return result;
	}

	public NoteResult<List<Share>> searchNote(String keyword,int page) {
		keyword="%"+keyword+"%";
		int begin=(page-1)*3;//计算抓取记录的起点
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("keyword", keyword);
		params.put("begin", begin);
		//模糊查询
		List<Share> list=shareDao.findByKeyword(params);
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("搜索完成！");
		result.setData(list);
		return result;
	}

}
