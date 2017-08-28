package com.dta.mediatic.adherent.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dta.mediatic.adherent.dao.AdherentRepository;
import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.cotisation.model.Cotisation;
@Service
public class AdherentGenerator{
	
	@Autowired
	AdherentService adherentService;
	
	
	static final int SIZE=200;
	@Transactional
	//@Synchronize(value = { "" })
	public Iterable<Adherent> saveAll(){
		List<Adherent>listAdherents=new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			
			Adherent a=new Adherent();
			a.setCp(ThreadLocalRandom.current().nextLong(10000,90001)+"");
			a.setEmail(genereStringEmail()+genereFinEmail());
			a.setCotisation(genreCotisation());
			a.setNom(genereString());
			a.setPrenom(genereString());
			a.setDateNaissance(genereDate());
			
			a.setAdresse(ThreadLocalRandom.current().nextInt(1,17)+" "+genereString());
			
			a.setVille(genereString());
			
			listAdherents.add(a);
		}
		return adherentService.save(listAdherents);
	}
	

	
	
	String genereString() {
		String s="";
		for(int i=0;i<10;i++) {
			char c=(char) ThreadLocalRandom.current().nextInt(65,90+1);
			s+=c;
		}
		return s;
	}
	LocalDate genereDate() {
		int year=ThreadLocalRandom.current().nextInt(1970,2000);
		int month=ThreadLocalRandom.current().nextInt(1,13);
		int dayOfMonth=ThreadLocalRandom.current().nextInt(1,29);
		
		return LocalDate.of(year, month, dayOfMonth);
		
		
	}
	String genereStringEmail() {
		String s="";
		for(int i=0;i<5;i++) {
			char c=(char) ThreadLocalRandom.current().nextInt(65,90+1);
			s+=c;
		}
		s+="@";
		
		
		return s;
				
		
	}
	String genereFinEmail() {
		String s="";
		for(int i=0;i<6;i++) {
			char c=(char) ThreadLocalRandom.current().nextInt(65,90+1);
			s+=c;
		}
		s+=".fr";
		return s;
	}
	Cotisation genreCotisation() {
		Cotisation c=new Cotisation();
		c.setDatePaiement(genereDate());
		c.setDateFinAbonnement(c.getDatePaiement().plusYears(1));
		c.setMontant((long) ThreadLocalRandom.current().nextInt(500,2001));
		
		return c;
	}
	
	
	
	
	
	
	
}
