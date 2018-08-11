package cn.augmentum.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.augmentum.cloud_note.entity.Share;

public interface ShareDao {

	public void save(Share share);
	
	public List<Share> findByKeyword(Map<String,Object> params);
}
