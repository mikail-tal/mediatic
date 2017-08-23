package com.dta.mediatic.cotisation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dta.mediatic.cotisation.model.Cotisation;
import com.dta.mediatic.cotisation.service.CotisationService;
@RestController
@RequestMapping("/mediatic/cotisation")
public class CotisationController {
	
	
		@Autowired
		private CotisationService cotisationService;

		@RequestMapping(value = "{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		//@JsonView(AdherentViews.class)
		public Cotisation findById(@PathVariable Long id){
		//	adherentService.findById(id).getEmprunt().forEach(e->System.out.println(e.getMedia().getAuteur()));
	        return cotisationService.findOne(id);
	    }
		
		@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		//@JsonView(AdherentViews.class)
		public Iterable<Cotisation> findAll() {
			//System.out.println("IL EST LA ");
	        return cotisationService.findAll();
	    }
		
		@RequestMapping(method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
		public void create(@RequestBody @Valid Cotisation resource) {	
			cotisationService.save(resource);
		}
		
		@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
		public Cotisation update(@PathVariable Long id, @RequestBody @Valid Cotisation resource) {
			//System.out.println(resource.getNom()+" "+resource.getCotisation().getMontant());
			return cotisationService.save(resource);
		}

}
