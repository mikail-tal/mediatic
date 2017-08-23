package com.dta.mediatic.media.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dta.mediatic.media.dao.MediaRepository;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.service.MediaticService;
@Service
public class MediaService implements MediaticService<Media>{
	@Autowired
	MediaRepository mediaRepository;

	@Override
	public long count() {
		return mediaRepository.count();
	}

	@Override
	public void delete(Long arg0) {
		mediaRepository.delete(arg0);
	}

	@Override
	public void delete(Media arg0) {
		mediaRepository.delete(arg0);
	}

	@Override
	public void delete(Iterable<? extends Media> arg0) {
		mediaRepository.delete(arg0);
	}

	@Override
	public void deleteAll() {
		mediaRepository.deleteAll();
	}

	@Override
	public boolean exists(Long arg0) {
		return mediaRepository.exists(arg0);
	}

	@Override
	public Iterable<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public Iterable<Media> findAll(Iterable<Long> arg0) {
		return mediaRepository.findAll(arg0);
	}

	@Override
	public Media findOne(Long arg0) {
		return mediaRepository.findOne(arg0);
	}

	@Override
	public <S extends Media> S save(S arg0) {
		return mediaRepository.save(arg0);
	}

	@Override
	public <S extends Media> Iterable<S> save(Iterable<S> arg0) {
		return mediaRepository.save(arg0);
	}
	
	
	
	/*
	
	
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
	

*/}
