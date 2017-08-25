package com.dta.mediatic.emprunt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dta.mediatic.emprunt.model.Emprunt;
@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

}
