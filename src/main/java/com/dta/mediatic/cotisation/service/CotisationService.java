package com.dta.mediatic.cotisation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dta.mediatic.cotisation.dao.CotisationRepository;
import com.dta.mediatic.cotisation.model.Cotisation;
import com.dta.mediatic.service.MediaticService;
@Service
public class CotisationService implements MediaticService<Cotisation>{
	@Autowired
	CotisationRepository cotisationRepository;

	@Override
	public long count() {
		return cotisationRepository.count();
	}

	@Override
	public void delete(Long arg0) {
		cotisationRepository.delete(arg0);
	}

	@Override
	public void delete(Cotisation arg0) {
		cotisationRepository.delete(arg0);
	}

	@Override
	public void delete(Iterable<? extends Cotisation> arg0) {
			cotisationRepository.delete(arg0);
	}

	@Override
	public void deleteAll() {
		cotisationRepository.deleteAll();
	}

	@Override
	public boolean exists(Long arg0) {
		return cotisationRepository.exists(arg0);
	}

	@Override
	public Iterable<Cotisation> findAll() {
		return cotisationRepository.findAll();
	}

	@Override
	public Iterable<Cotisation> findAll(Iterable<Long> arg0) {
		return cotisationRepository.findAll(arg0);
	}

	@Override
	public Cotisation findOne(Long arg0) {
		return cotisationRepository.findOne(arg0);
	}

	@Override
	public <S extends Cotisation> S save(S arg0) {
		return cotisationRepository.save(arg0);
	}

	@Override
	public <S extends Cotisation> Iterable<S> save(Iterable<S> arg0) {
		return cotisationRepository.save(arg0);
	}

	

	
	
	/*
	static CotisationDao cotisationDao;
	public static CotisationDao getInstance(){
		if(cotisationDao==null){
			cotisationDao=new CotisationDao();
		}
		return cotisationDao;
	}
	
	public void create(Cotisation cotisation){
		cotisationDao.create(cotisation);
	}

*/}
