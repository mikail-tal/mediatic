package com.dta.mediatic.adherent.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dta.mediatic.adherent.model.Adherent;
@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
	
    public Page<Adherent> findAllByOrderByNomAscPrenomAsc(Pageable pageable);
    public Page<Adherent> findAllByOrderByPrenomAsc(Pageable pageable);
    public Page<Adherent> findAllByOrderByPrenomDesc(Pageable pageable);
    public Page<Adherent> findAllByOrderByNomAsc(Pageable pageable);
    public Page<Adherent> findAllByOrderByNomDesc(Pageable pageable);
    public Page<Adherent> findAllByOrderByIdAsc(Pageable pageable);
    public Page<Adherent> findAllByOrderByIdDesc(Pageable pageable);
    public Page<Adherent> findAllByOrderByDateNaissanceAsc(Pageable pageable);
    public Page<Adherent> findAllByOrderByDateNaissanceDesc(Pageable pageable);
    public Page<Adherent> findByIdcharStartsWithAndNomIgnoreCaseContainingOrderByNomAscPrenomAsc(String id,String nom,Pageable pageable);
    public Page<Adherent> findByIdcharStartsWithOrNomIgnoreCaseContainingOrderByNomAscPrenomAsc(String id,String nom,Pageable pageable);
    public Page<Adherent> findByNomIgnoreCaseContainingOrderByNomAscPrenomAsc(String nom,Pageable pageable);
    public Page<Adherent> findByIdcharStartsWithOrderByNomAscPrenomAsc(String idChar,Pageable pageable);
    

}
