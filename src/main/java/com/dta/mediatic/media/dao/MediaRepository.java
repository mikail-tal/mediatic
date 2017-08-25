package com.dta.mediatic.media.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;
@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
	//public Page<Media> findAllByOrderByNomAscPrenomAsc(Pageable pageable);
    public Page<Media> findAllByOrderByTitreAsc(Pageable pageable);
    
    public Page<Media> findAllByOrderByTitreDesc(Pageable pageable);
    public Page<Media> findAllByOrderByTypeAsc(Pageable pageable);
    public Page<Media> findAllByOrderByTypeDesc(Pageable pageable);
    public Page<Media> findAllByOrderByAuteurAsc(Pageable pageable);
    public Page<Media> findAllByOrderByAuteurDesc(Pageable pageable);
    public Page<Media> findAllByOrderByIdAsc(Pageable pageable);
    public Page<Media> findAllByOrderByIdDesc(Pageable pageable);
    
    
   /* public Page<Media> findAllByOrderByDateNaissanceAsc(Pageable pageable);
    public Page<Media> findAllByOrderByDateNaissanceDesc(Pageable pageable);
    */
    
    
    //public Page<Media> findByTitreStartsWithAndNomIgnoreCaseContainingOrderByNomAscPrenomAsc(String id,String nom,Pageable pageable);
  
   // @Query("SELECT M FROM Media AS M")
    public Page<Media> findByTitreIgnoreCaseContainingOrAuteurIgnoreCaseContainingOrderByTitreAsc
    (String titre,String auteur,
    		Pageable pageable);
    public Page<Media> findByTitreIgnoreCaseContainingAndAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc
    (String titre
    		,String auteur
    		,TypeMedia type
    		,Pageable pageable);
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



    public Page<Media> findByTitreIgnoreCaseContainingOrderByTitreAsc(String titre,Pageable pageable);
    public Page<Media> findByTypeOrderByTitreAsc(TypeMedia type,Pageable pageable);
    public Page<Media> findByAuteurIgnoreCaseContainingOrderByTitreAsc(String auteur,Pageable pageable);
	
	

}
