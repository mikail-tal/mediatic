package com.dta.mediatic.garbage;

import java.time.LocalDate;
import java.util.List;

import com.dta.mediatic.cotisation.model.Cotisation;

public interface IAdherentGenerator {
	
	List<String> genereNom();
	List<String> generePrenom();
	List<LocalDate>genereDateNaissance();
	List<String> genereEmail();
	List<String> genereAdresse();
	List<Integer> genereCodeP();
	List<Cotisation> gerenreCotisation();

}
