package com.dta.mediatic.garbage;

import java.io.IOException;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class EmpruntEnCoursDeserializer extends StdDeserializer<Emprunt>{

	protected EmpruntEnCoursDeserializer() {
		super(Emprunt.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Emprunt deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Emprunt e=p.readValueAs(Emprunt.class);
		e.setAdherent(null);
		
		return e;
	}

}
