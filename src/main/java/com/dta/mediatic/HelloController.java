package com.dta.mediatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dta.mediatic.adherent.service.AdherentGenerator;
import com.dta.mediatic.adherent.service.AdherentService;
import com.dta.mediatic.media.service.MediaGenerator;
import com.dta.mediatic.media.service.MediaService;

@Controller
public class HelloController {
	
	@Autowired
	AdherentGenerator adherentGenerator;
	@Autowired
	MediaGenerator mediaGenerator;
	@RequestMapping("/mediatic")
	@ResponseBody
	String home() {
		//adherentGenerator.saveAll();
		//mediaGenerator.saveAll();
		
		return "HELLO WORLD";
	}
	
}
