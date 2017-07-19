package media.service;

import java.time.LocalDate;

import media.dao.MediaDao;
import media.model.Media;

public class MediaService {
	
	
	static MediaDao mediaDao;
	public MediaDao getInstance(){
		if(mediaDao==null){
			mediaDao=new MediaDao();
		}
		return mediaDao;
	}
	public void create(Media media){
		mediaDao.create(media);
	}
	

}
