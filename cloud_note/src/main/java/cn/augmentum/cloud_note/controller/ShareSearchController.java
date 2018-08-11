package cn.augmentum.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.augmentum.cloud_note.entity.Share;
import cn.augmentum.cloud_note.service.ShareService;
import cn.augmentum.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareSearchController {

	@Resource
	private ShareService shareService;
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> execute(String keyword,int page){
		NoteResult<List<Share>> result=shareService.searchNote(keyword,page);
		return result;
	}
}
