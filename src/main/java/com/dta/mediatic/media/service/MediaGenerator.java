package com.dta.mediatic.media.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dta.mediatic.media.dao.MediaRepository;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;

@Service
public class MediaGenerator {
	
	
	static final int SIZE=100;
	
	@Autowired
	MediaRepository mediaRepository;
	
	@Transactional
	public Iterable<Media> saveAll(){
		List<Media> medias=new ArrayList<>();
		
		for(int i=0;i<SIZE;i++) {
			Media m=new Media();
			m.setAuteur(genereString());
			m.setTitre(genereString());
			m.setType(genreTypeMedia());
			medias.add(m);
		}
		return mediaRepository.save(medias);
				
	}
	
	
	
	
	
	
	String genereString() {
		String s="";
		for(int i=0;i<10;i++) {
			char c=(char) ThreadLocalRandom.current().nextInt(65,90+1);
			s+=c;
		}
		return s;
	}
	TypeMedia genreTypeMedia() {
		TypeMedia typeMedia=TypeMedia.CD;
		int key=ThreadLocalRandom.current().nextInt(1,4);
		switch (key) {
		case 1:typeMedia=TypeMedia.CD;break;
		case 2:typeMedia=TypeMedia.DVD;break;
		case 3:typeMedia=TypeMedia.LIVRE;break;
		}
		
		return typeMedia;
	}

}
