package com.dta.mediatic.garbage;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import com.dta.mediatic.adherent.model.Adherent;

public class AdherentConverter implements Converter<Page<Adherent>, Page<Adherent>>{

	@Override
	public Page<Adherent> convert(Page<Adherent> source) {
		
		
		return null;
	}

	

}
