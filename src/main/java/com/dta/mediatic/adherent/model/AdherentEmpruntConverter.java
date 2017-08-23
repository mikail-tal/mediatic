package com.dta.mediatic.adherent.model;

import java.util.ArrayList;
import java.util.List;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.dta.mediatic.media.model.Media;
import com.fasterxml.jackson.databind.util.StdConverter;

public class AdherentEmpruntConverter extends StdConverter<List<Emprunt>, List<Emprunt>>{

	@Override
	public List<Emprunt> convert(List<Emprunt> value) {
		List<Emprunt> emprunts=new ArrayList<>();
		for (Emprunt emprunt : value) {
			Media media=new Media(emprunt.getMedia().getId()
					, emprunt.getMedia().getTitre()
					,emprunt.getMedia().getAuteur()
					,emprunt.getMedia().getType());
					
		Emprunt e=new Emprunt(emprunt.getId(), media);
			emprunts.add(e);
		}
		
		
		return emprunts;
	}

}
