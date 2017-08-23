package com.dta.mediatic.media.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class EnumDeserializer extends StdDeserializer<Enum<TypeMedia>>{

	protected EnumDeserializer() {
		super(TypeMedia.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TypeMedia deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String s=p.readValueAs(String.class);
		
		return Enum.valueOf(TypeMedia.class, s.trim());
	}
	
	
	

}
