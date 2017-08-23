package com.dta.mediatic.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 1L;

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }


    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
    	
    	
   /*Date d= 	jp.readValueAs(Date.class);
	Instant instant=d.toInstant();
	ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    	*/
   /* 	Long  l=Date.parse(jp.readValueAs(Date.class));
    	Date d=new Date(l);
    	Instant instant=d.toInstant();
    	ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    	
    	System.out.println("DATE");*/
    //	System.out.println(jp.getCurrentValue()+ " CURRENT VALUE");
   // 	System.out.println(jp.readValueAs(String.class) +" READ VALUE AS");
    	String s=jp.readValueAs(String.class);
    	Instant instant=Instant.parse(s);
    	System.out.println(instant);
    	LocalDateTime result=LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    	System.out.println(result);

    	System.out.println(result.toLocalDate().plusDays(1));
   // 	String date=s.substring(0, s.indexOf('T'));
   // 	System.out.println();
   // 	localdateti
    			
   // 	System.out.println(LocalDateTime.parse(jp.readValueAs(String.class),DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ "  UNE DATE");
        return result.toLocalDate().plusDays(1);
    }

}
