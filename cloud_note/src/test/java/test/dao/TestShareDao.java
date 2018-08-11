package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.augmentum.cloud_note.dao.NoteDao;
import cn.augmentum.cloud_note.dao.ShareDao;
import cn.augmentum.cloud_note.entity.Note;
import cn.augmentum.cloud_note.entity.Share;
import cn.augmentum.cloud_note.util.NoteUtil;
import test.TestBase;

public class TestShareDao extends TestBase{
	private NoteDao noteDao;
	private ShareDao shareDao;
	@Before
	public void init(){
	   noteDao=super.getContext().getBean("noteDao",NoteDao.class);
	   shareDao=super.getContext().getBean("shareDao",ShareDao.class);
	}

	@Test
	public void testNoteload(){
		Note note=noteDao.findByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
		System.out.println(note.getCn_note_title());
		System.out.println(note.getCn_note_body());
		System.out.println(note.getCn_note_id());
	}

	@Test
	public void testaddShare(){
		Note note=noteDao.findByNoteId("07305c91-d9fa-420d-af09-c3ff209608ff");
		Share share=new Share();
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title("Dylan");
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_note_id(note.getCn_note_id());
		shareDao.save(share);
		System.out.println(share);
	}
	/*@Test
	public void testfindByKeyword(){
		List<Share> list=shareDao.findByKeyword("≤‚ ‘");
		for(Share share:list){
			System.out.println(share.getCn_share_id()+","+share.getCn_share_title());
		}
	}*/
}
