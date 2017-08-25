package com.dta.mediatic.media.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;

public interface IMediaService {
    public Page<Media> orderBy(String field,String order,Pageable pageable);
    public Page<Media> findByTitreIgnoreCaseContainingOrAuteurIgnoreCaseContainingOrderByTitreAsc
    (String titre,String auteur,
    		Pageable pageable);
    public Page<Media> findByTitreIgnoreCaseContainingAndAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(String titre,String auteur,TypeMedia type,Pageable pageable);
	public Page<Media> findByTitreIgnoreCaseContainingOrderByTitreAsc(String titre,Pageable pageable);
    public Page<Media> findByTypeOrderByTitreAsc(TypeMedia type,Pageable pageable);
    public Page<Media> findByAuteurIgnoreCaseContainingOrderByTitreAsc(String auteur,Pageable pageable);
    public Page<Media> findAllByOrderByTitreAsc(Pageable pageable);
    public Page<Media> findByTitreIgnoreCaseContainingAndTypeOrderByTitreAsc
    (String titre
    		,TypeMedia type
    		,Pageable pageable);
    public Page<Media> findByAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc
    (String auteur
    		,TypeMedia type
    		,Pageable pageable);
    public Page<Media> findByAuteurIgnoreCaseContainingAndTitreIgnoreCaseContainingOrderByTitreAsc
    (String auteur
    		,String titre
    		,Pageable pageable);

	

}
