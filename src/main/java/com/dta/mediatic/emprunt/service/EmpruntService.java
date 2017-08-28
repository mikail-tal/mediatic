package com.dta.mediatic.emprunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dta.mediatic.adherent.dao.AdherentRepository;
import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.emprunt.dao.EmpruntRepository;
import com.dta.mediatic.emprunt.model.Emprunt;
import com.dta.mediatic.media.dao.MediaRepository;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.service.MediaticService;
@Service
public class EmpruntService implements MediaticService<Emprunt>{
	@Autowired
	EmpruntRepository empruntRepository;
	@Autowired
	MediaRepository mediaRepository;
	@Autowired
	AdherentRepository adherentRepository;

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return empruntRepository.count();
	}

	@Override
	public void delete(Long arg0) {
		empruntRepository.delete(arg0);
	}

	@Override
	public void delete(Emprunt arg0) {
			empruntRepository.delete(arg0);
	}

	@Override
	public void delete(Iterable<? extends Emprunt> arg0) {
			empruntRepository.delete(arg0);
	}

	@Override
	public void deleteAll() {
		empruntRepository.deleteAll();
	}

	@Override
	public boolean exists(Long arg0) {
		return empruntRepository.exists(arg0);
	}

	@Override
	public Iterable<Emprunt> findAll() {
		return empruntRepository.findAll();
	}

	@Override
	public Iterable<Emprunt> findAll(Iterable<Long> arg0) {
		return empruntRepository.findAll(arg0);
	}

	@Override
	public Emprunt findOne(Long arg0) {
		return empruntRepository.findOne(arg0);
	}
	

	@Override
	public <S extends Emprunt> S save(S arg0) {
		empruntRepository.save(arg0);
		Media m=mediaRepository.findOne(arg0.getMedia().getId());
		m.setEmpruntEnCours(arg0);
		Adherent a=adherentRepository.findOne(arg0.getAdherent().getId());
		a.incrementMedia();
		mediaRepository.save(m);
		adherentRepository.save(a);
		
		return arg0;
	}

	@Override
	public <S extends Emprunt> Iterable<S> save(Iterable<S> arg0) {
		return empruntRepository.save(arg0);
	}

	
	
	
	
	/*
	private static EmpruntService empruntService;
	
	public static EmpruntService getInstance(){
		if(empruntService==null){
			empruntService=new EmpruntService();
			
		}
		return empruntService;
	}
	
	public EmpruntDao getEmpruntDao() {
		return EmpruntDao.getInstance();
	}

	private EmpruntService(){
		
	}
	
	public void create(Emprunt emprunt){
		assignDateRetour(emprunt);
		getEmpruntDao().create(emprunt);
	}
	
	public void assignDateRetour(Emprunt emprunt){
		TypeMedia t=emprunt.getMedia().getType();
		if(t.equals(TypeMedia.CD) || t.equals(t.equals(TypeMedia.DVD))){
			emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(15));
		}else {
			emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(30));
		}
		
		
		
	}
	public void emprunter(Emprunt emprunt){
		if(emprunt.getMedia().getEmpruntEnCours()==null){
			create(emprunt);
			emprunt.getMedia().setEmpruntEnCours(emprunt);
			MediaService.getInstance().update(emprunt.getMedia());
		}
		
		
		
	}

*/}
