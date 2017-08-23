package com.dta.mediatic.garbage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AdherentListEmpruntDeserializer extends StdDeserializer<List<Emprunt>>{

	protected AdherentListEmpruntDeserializer() {
		super(List.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Emprunt> deserialize(JsonParser p, DeserializationContext ctxt)
			  {
		List<Emprunt> emprunts=new ArrayList<>();
		
		Emprunt e;
		try {
			System.out.println("A TRY");
			e = p.readValueAs(Emprunt.class);
		} catch (IOException  e1) {
			System.out.println("NUUULLL");
			return null;
		}
		e.setAdherent(null);
		e.getMedia().setEmprunt(null);
		e.getMedia().setEmpruntEnCours(null);
		System.out.println(e);
		emprunts.add(e);
		
		
		
		
		return emprunts;
	}

}
