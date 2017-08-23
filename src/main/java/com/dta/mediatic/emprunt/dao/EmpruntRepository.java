package com.dta.mediatic.emprunt.dao;

import org.springframework.data.repository.CrudRepository;

import com.dta.mediatic.emprunt.model.Emprunt;

public interface EmpruntRepository extends CrudRepository<Emprunt, Long> {

}
