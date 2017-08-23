package com.dta.mediatic.media.model;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EmpruntSerializerMedia extends StdSerializer<Emprunt>{

	protected EmpruntSerializerMedia() {
		super(Emprunt.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Emprunt value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		System.out.println("DANS L EMPRUNT EN COURS");
		
		gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeObjectFieldStart("adherent");
        gen.writeNumberField("id",value.getAdherent().getId());
        gen.writeStringField("nom", value.getAdherent().getNom());
        gen.writeStringField("prenom", value.getAdherent().getPrenom());
        gen.writeEndObject();
        gen.writeStringField("dateEmprunt", value.getDateEmprunt().format(DateTimeFormatter.ISO_LOCAL_DATE));
        gen.writeStringField("dateRetourPrevue", value.getDateRetourPrevue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        gen.writeEndObject();
		
	}

}
