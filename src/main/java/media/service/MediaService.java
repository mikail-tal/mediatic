package media.service;

import media.dao.MediaDao;
import media.model.Media;

public class MediaService {
	
	
	static MediaDao mediaDao;
	public static MediaDao getInstance(){
		if(mediaDao==null){
			mediaDao=MediaDao.getInstance();
		}
		return mediaDao;
	}
	public void create(Media media){
		mediaDao.create(media);
	}
	

}
