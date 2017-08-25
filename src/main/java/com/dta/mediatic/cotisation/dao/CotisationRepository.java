package com.dta.mediatic.cotisation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dta.mediatic.cotisation.model.Cotisation;
@Repository
public interface CotisationRepository extends JpaRepository<Cotisation, Long>{

}
