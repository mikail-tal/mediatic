package com.dta.mediatic.adherent.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dta.mediatic.adherent.model.Adherent;

public interface IAdherentService <T> {
	 	public Page<Adherent> findAllOrderByNomAndPrenomAsc(Pageable pageable);
	    
	    
	    public Page<Adherent> orderBy(String field,String order,Pageable pageable);

	    
	    
	    
	    
	    public Page<Adherent> findByIdcharStartsWithAndNomIgnoreCaseContaining(String id,String nom,Pageable pageable);
	    public Page<Adherent> findByIdcharStartsWithOrNomIgnoreCaseContaining(String id,String nom,Pageable pageable);
	    public Page<Adherent> findByNomIgnoreCaseContaining(String nom,Pageable pageable);
	    public Page<Adherent> findByIdcharStartsWith(String idChar,Pageable pageable);
	    
}
