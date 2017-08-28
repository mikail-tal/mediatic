package com.dta.mediatic.adherent.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dta.mediatic.adherent.dao.AdherentRepository;
import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.service.MediaticService;
import com.google.common.collect.Lists;

@Service
public class AdherentService implements MediaticService<Adherent>, IAdherentService<Adherent> {

	@Autowired
	AdherentRepository adherentRepository;

	@Override
	public long count() {
		return adherentRepository.count();
	}

	@Override
	public void delete(Long arg0) {
		adherentRepository.delete(arg0);

	}

	@Override
	public void delete(Adherent arg0) {
		adherentRepository.delete(arg0);
	}

	@Override
	public void delete(Iterable<? extends Adherent> arg0) {
		adherentRepository.delete(arg0);
	}

	@Override
	public void deleteAll() {
		adherentRepository.deleteAll();
	}

	@Override
	public boolean exists(Long arg0) {
		return adherentRepository.exists(arg0);
	}

	@Override
	public Iterable<Adherent> findAll() {
		return adherentRepository.findAll();
	}

	@Override
	public Iterable<Adherent> findAll(Iterable<Long> arg0) {
		return adherentRepository.findAll(arg0);
	}

	@Override
	public Adherent findOne(Long arg0) {
		return adherentRepository.findOne(arg0);
	}

	@Override
	public <S extends Adherent> S save(S arg0) {
		Adherent a = adherentRepository.save(arg0);
		arg0.setIdchar(a.getId().toString());
		arg0.setAjour(a.getAjour());
		return adherentRepository.save(arg0);
	}

	@Override
	public <S extends Adherent> Iterable<S> save(Iterable<S> arg0) {
		/*//List<Adherent>ads=Lists.newArrayList(arg0);
		Iterable<S>ads=arg0;
		ads.forEach(a->a.setAjour());
		arg0=ads;
		//ads.stream()
		 * 
*/		
		List<S>ads=adherentRepository.save(arg0);
		ads.forEach(a->a.setAjour(a.getAjour()));
		ads.forEach(a->a.setIdchar(a.getId().toString()));
		//ads.forEach(a->System.out.println(a.getId()));
		
		
		
		return adherentRepository.save(ads);
	}

	@Override
	public Page<Adherent> findByIdcharStartsWithAndNomIgnoreCaseContaining(String id, String nom, Pageable pageable) {
		return adherentRepository.findByIdcharStartsWithAndNomIgnoreCaseContainingOrderByNomAscPrenomAsc(id, nom, pageable);
	}

	@Override
	public Page<Adherent> findByIdcharStartsWithOrNomIgnoreCaseContaining(String id, String nom, Pageable pageable) {
		return adherentRepository.findByIdcharStartsWithOrNomIgnoreCaseContainingOrderByNomAscPrenomAsc(id, nom, pageable);
	}

	@Override
	public Page<Adherent> findByNomIgnoreCaseContaining(String nom, Pageable pageable) {
		return adherentRepository.findByNomIgnoreCaseContainingOrderByNomAscPrenomAsc(nom, pageable);
	}

	@Override
	public Page<Adherent> orderBy(String field, String order, Pageable pageable) {

		Comparator<Adherent> comparatorNbrMedia = ((a1, a2) -> Long.compare(a1.getNbrMedia(), a2.getNbrMedia()));
		Comparator<Adherent> comparatorAJour = ((a1, a2) -> a1.getAjour().compareTo(a2.getAjour()));
		

		switch (field) {
		case "nom":
			switch (order) {
			case "asc":
				return adherentRepository.findAllByOrderByNomAsc(pageable);
			case "desc":
				return adherentRepository.findAllByOrderByNomDesc(pageable);
			}

		case "prenom":
			switch (order) {
			case "asc":
				return adherentRepository.findAllByOrderByPrenomAsc(pageable);
			case "desc":
				return adherentRepository.findAllByOrderByPrenomDesc(pageable);
			}
		case "datenaissance":
			switch (order) {
			case "asc":
				return adherentRepository.findAllByOrderByDateNaissanceAsc(pageable);
			case "desc":
				return adherentRepository.findAllByOrderByDateNaissanceDesc(pageable);
			}
		case "id":
			switch (order) {
			case "asc":
				return adherentRepository.findAllByOrderByIdAsc(pageable);
			case "desc":
				return adherentRepository.findAllByOrderByIdDesc(pageable);
			}
		case "ajour":
			switch (order) {
			case "asc": {
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.ASC,"ajour"));
				
				return adherentRepository.findAll(pq);
			}
			case "desc": {
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.DESC,"ajour"));

				return adherentRepository.findAll(pq);
			}
			}
		case "nbrMedia":
			switch (order) {
			case "asc": {
				return adherentRepository.findAllByOrderByNbrMediaAsc(pageable);
			}
			case "desc": {
				return adherentRepository.findAllByOrderByNbrMediaDesc(pageable);
			}
			}
		}

		return findAllOrderByNomAndPrenomAsc(pageable);
	}

	@Override
	public Page<Adherent> findAllOrderByNomAndPrenomAsc(Pageable pageable) {
		return adherentRepository.findAllByOrderByNomAscPrenomAsc(pageable);
	}

	@Override
	public Page<Adherent> findByIdcharStartsWith(String idChar, Pageable pageable) {
		return adherentRepository.findByIdcharStartsWithOrderByNomAscPrenomAsc(idChar, pageable);
	}
	
	
	
	/*public Page<Adherent> convert(Comparator<Adherent> comparator,Pageable pageable){
		//Page<Adherent> source0=adherentRepository.findAllByOrderByIdDesc(pageable);
		//Page<Adherent>source=adherentRepository.findAll(pageable).so
		PageRequest pageRequest=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),
				
				new Sort(Direction.ASC,"nbrMedia")
				);
		//Iterable<Adherent>source0=adherentRepository.findAll();
		Page<Adherent>source=adherentRepository.findAll(pageRequest);
		int total=pageable.getPageSize();
		//pageable.getOffset()
		System.out.println(total+"     TOTAAAAAAAl");
		int start=pageable.getPageNumber()*total;
		int end=start+total;
		
		
	
		List<Adherent> list=Lists.newArrayList(source0);
		list.sort(comparator.reversed());
		list.forEach(a->System.out.println(a.getNbrMedia()));
		
		
		return source;//new PageImpl<>(list, new PageRequest(0, 10), total);
	}*/

	/*
	 * static AdherentDao adherentDao;
	 * 
	 * public static AdherentDao getInstance(){ if(adherentDao==null){ adherentDao=
	 * AdherentDao.getInstance(); } return adherentDao; }
	 * 
	 * public void create(Adherent adherent){ adherentDao.create(adherent); }
	 */

}
