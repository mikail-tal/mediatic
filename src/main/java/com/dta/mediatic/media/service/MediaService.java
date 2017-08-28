package com.dta.mediatic.media.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dta.mediatic.media.dao.MediaRepository;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;
import com.dta.mediatic.service.MediaticService;
import com.google.common.collect.Lists;
@Service
public class MediaService implements MediaticService<Media>,IMediaService{
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

	

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingOrderByTitreAsc(String titre, Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingOrderByTitreAsc(titre, pageable);
	}

	@Override
	public Page<Media> findByTypeOrderByTitreAsc(TypeMedia type, Pageable pageable) {
		return mediaRepository.findByTypeOrderByTitreAsc(type, pageable);
	}

	@Override
	public Page<Media> findByAuteurIgnoreCaseContainingOrderByTitreAsc(String auteur, Pageable pageable) {
		return mediaRepository.findByAuteurIgnoreCaseContainingOrderByTitreAsc(auteur, pageable);
	}

	@Override
	public Page<Media> orderBy(String field, String order, Pageable pageable) {

		Comparator<Media> comparatorDateRetourPrevue = ((e1, e2) -> e1.getEmpruntEnCours()
				.getDateRetourPrevue()
				.compareTo
				(e2.getEmpruntEnCours()
						.getDateRetourPrevue()));
		Comparator<Media> comparatorEmprunteePar = ((e1, e2) -> e1.getEmpruntEnCours()
				.getAdherent().getNom()
				.compareTo
				(e2.getEmpruntEnCours().getAdherent().getNom()));
		Comparator<Media> comparator=comparatorEmprunteePar.
				thenComparing
				((e1,e2)->e1.getEmpruntEnCours()
						.getAdherent().getPrenom()
						.compareTo
						(e2.getEmpruntEnCours().getAdherent().getPrenom()));
		

		switch (field) {
		case "id":
			switch (order) {
			case "asc":
				return mediaRepository.findAllByOrderByIdAsc(pageable);
			case "desc":
				return mediaRepository.findAllByOrderByIdDesc(pageable);
			}
		case "type":
			switch (order) {
			case "asc":
				return mediaRepository.findAllByOrderByTypeAsc(pageable);
			case "desc":
				return mediaRepository.findAllByOrderByTypeDesc(pageable);
			}

		case "titre":
			switch (order) {
			case "asc":
				return mediaRepository.findAllByOrderByTitreAsc(pageable);
			case "desc":
				return mediaRepository.findAllByOrderByTitreDesc(pageable);
			}
		case "auteur":
			switch (order) {
			case "asc":
				return mediaRepository.findAllByOrderByAuteurAsc(pageable);
			case "desc":
				return mediaRepository.findAllByOrderByAuteurDesc(pageable);
			}
		case "emprunteepar":
			switch (order) {
			case "asc":{
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.ASC,"Emprunt.adherent.nom"));

				return mediaRepository.findAll(pq);
						
			}			//convert(comparator, pageable);//mediaRepository.findAllByOrderBy(pageable);
			case "desc":
			{
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.DESC,"Emprunt.adherent.nom"));

				return  mediaRepository.findAll(pq);
			}			
						//convertReverse(comparator.reversed(), pageable);//mediaRepository.findAllByOrderByIdDesc(pageable);
			}
		case "dateretourprevue":
			switch (order) {
			case "asc": {
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.ASC,"Emprunt.dateRetourPrevue"));
				return mediaRepository.findAll(pq);

				//return convert(comparatorDateRetourPrevue, pageable);
			}
			case "desc": {
				PageRequest pq=new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),new Sort(Direction.DESC,"Emprunt.dateRetourPrevue"));
				return mediaRepository.findAll(pq);
				
				//return convertReverse(comparatorDateRetourPrevue.reversed(), pageable);
			}
			}
		
		}

		return findAllByOrderByTitreAsc(pageable);
	
		
		
		
		
	}
	
	
	public Page<Media> convert(Comparator<Media> comparator,Pageable pageable){/*
		//Page<Media> source=mediaRepository.findAllByOrderByTitreAsc(pageable);
		Iterable<Media>source=mediaRepository.findAll();
		int total=pageable.getPageSize();
		//int total=source.getTotalPages();
	
		List<Media> list=Lists.newArrayList(source);
		List<Media>listNotNull=list.stream().filter(m->m.getEmpruntEnCours()!=null)
		.collect(Collectors.toList());
		listNotNull.sort(comparator);
		List<Media>listNull=list.stream().filter(m->m.getEmpruntEnCours()==null).collect(Collectors.toList());
		listNull.sort((m1,m2)->m1.getTitre().compareTo(m2.getTitre()));
		
		listNotNull.addAll(listNull);
		
		return new PageImpl<>(listNotNull, pageable, total);
	*/
	return null;	
	}
	public Page<Media> convertReverse(Comparator<Media> comparator,Pageable pageable){
		//Page<Media> source=mediaRepository.findAllByOrderByTitreAsc(pageable);
		Iterable<Media>source=mediaRepository.findAll();
		int total=pageable.getPageSize();
	
		List<Media> list=Lists.newArrayList(source);
		List<Media>listNotNull=list.stream().filter(m->m.getEmpruntEnCours()!=null)
		.collect(Collectors.toList());
		listNotNull.sort(comparator);
		List<Media>listNull=list.stream().filter(m->m.getEmpruntEnCours()==null).collect(Collectors.toList());
		listNull.sort((m1,m2)->m1.getTitre().compareTo(m2.getTitre()));
		
		listNull.addAll(listNotNull);
		
		return new PageImpl<>(listNull, pageable, total);
	}

	@Override
	public Page<Media> findAllByOrderByTitreAsc(Pageable pageable) {
		return mediaRepository.findAllByOrderByTitreAsc(pageable);
	}

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingAndAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(
			String titre, String auteur, TypeMedia type, Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingAndAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(titre, auteur, type, pageable);
	}

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingAndTypeOrderByTitreAsc(String titre,
			TypeMedia type, Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingAndTypeOrderByTitreAsc(titre, type, pageable);
	}

	@Override
	public Page<Media> findByAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(String auteur,
			TypeMedia type, Pageable pageable) {
		return mediaRepository.findByAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(auteur, type, pageable);
	}

	@Override
	public Page<Media> findByAuteurIgnoreCaseContainingAndTitreIgnoreCaseContainingOrderByTitreAsc(String auteur,
			String titre, Pageable pageable) {
		return mediaRepository.findByAuteurIgnoreCaseContainingAndTitreIgnoreCaseContainingOrderByTitreAsc(auteur, titre, pageable);
	}

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingOrAuteurIgnoreCaseContainingOrderByTitreAsc(
			String titre,String auteur, Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingOrAuteurIgnoreCaseContainingOrderByTitreAsc(titre,auteur, pageable);
	}

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingAndTypeAndEmpruntEnCoursIsNull(String titre, TypeMedia type,Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingAndTypeAndEmpruntEnCoursIsNull(titre, type,pageable);
	}

	@Override
	public Page<Media> findByTitreIgnoreCaseContainingAndEmpruntEncoursIsNull(String titre,Pageable pageable) {
		return mediaRepository.findByTitreIgnoreCaseContainingAndEmpruntEnCoursIsNull(titre,pageable);
	}

	@Override
	public Page<Media> findByEmpruntEnCoursIsNull(Pageable pageable) {
		return findByEmpruntEnCoursIsNull(pageable);
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
