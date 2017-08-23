package com.dta.mediatic.garbage;

import java.io.IOException;
import java.util.List;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AdherentListEmpruntSerializer extends StdSerializer<List<Emprunt>> {

	

	protected AdherentListEmpruntSerializer(Class<List<Emprunt>> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(List<Emprunt> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			
		
		
	}
	

}
