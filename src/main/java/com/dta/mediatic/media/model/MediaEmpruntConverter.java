package com.dta.mediatic.media.model;

import java.util.ArrayList;
import java.util.List;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.databind.util.StdConverter;

public class MediaEmpruntConverter extends StdConverter<List<Emprunt>, List<Emprunt>> {

	@Override
	public List<Emprunt> convert(List<Emprunt> value) {
		if(value.isEmpty()) {
			return new ArrayList<>();
		}
		List<Emprunt> emprunts=new ArrayList<>();
		
		for (Emprunt emprunt : value) {
			System.out.println("DANS LA LISTE");
			Adherent adherent=new Adherent(emprunt.getAdherent().getId()
					, emprunt.getAdherent().getNom()
					, emprunt.getAdherent().getPrenom()
					, emprunt.getAdherent().getDateNaissance()
					, emprunt.getAdherent().getCotisation());
			Emprunt e=new Emprunt(emprunt.getId(), adherent);
			emprunts.add(e);
		}
		
		
		return emprunts;
	}

}
