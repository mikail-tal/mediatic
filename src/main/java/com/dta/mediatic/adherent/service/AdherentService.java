package com.dta.mediatic.adherent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dta.mediatic.adherent.dao.AdherentRepository;
import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.service.MediaticService;
@Service
public class AdherentService implements MediaticService<Adherent>{
	
	
	@Autowired
	AdherentRepository adherentRepository;

	
	public long count() {
		return  adherentRepository.count();
	}

	
	public void delete(Long arg0) {
		adherentRepository.delete(arg0);
		
	}

	
	public void delete(Adherent arg0) {
		adherentRepository.delete(arg0);
	}

	
	public void delete(Iterable<? extends Adherent> arg0) {
			adherentRepository.delete(arg0);
	}

	
	public void deleteAll() {
			adherentRepository.deleteAll();
	}

	
	public boolean exists(Long arg0) {
		return adherentRepository.exists(arg0);
	}

	
	public Iterable<Adherent> findAll() {
		return adherentRepository.findAll();
	}

	
	public Iterable<Adherent> findAll(Iterable<Long> arg0) {
		return adherentRepository.findAll(arg0);
	}

	
	public Adherent findOne(Long arg0) {
		return adherentRepository.findOne(arg0);
	}

	
	public <S extends Adherent> S save(S arg0) {
		return adherentRepository.save(arg0);
	}

	
	public <S extends Adherent> Iterable<S> save(Iterable<S> arg0) {
		return adherentRepository.save(arg0);
	}
	
	
	
	
	
	
	/*static AdherentDao adherentDao;
	
	public static AdherentDao getInstance(){
		if(adherentDao==null){
			adherentDao= AdherentDao.getInstance();
		}
		return adherentDao;
	}
	
	public void create(Adherent adherent){
		adherentDao.create(adherent);		
	}
	*/
	
	

}
