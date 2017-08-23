package com.dta.mediatic.garbage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class MediaListEmpruntDeserializer extends StdDeserializer<List<Emprunt>>{

	protected MediaListEmpruntDeserializer() {
		super(List.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Emprunt> deserialize(JsonParser p, DeserializationContext ctxt){
		
		
		
		
		
		return null;
	}

}
