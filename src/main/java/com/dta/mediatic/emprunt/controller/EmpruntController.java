package com.dta.mediatic.emprunt.controller;

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

import com.dta.mediatic.emprunt.model.Emprunt;
import com.dta.mediatic.emprunt.service.EmpruntService;
@RestController
@RequestMapping("/mediatic/emprunt")
public class EmpruntController {
	

	
	
	@Autowired
	private EmpruntService empruntService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//@JsonView(AdherentViews.class)
	public Emprunt findById(@PathVariable Long id){
	//	adherentService.findById(id).getEmprunt().forEach(e->System.out.println(e.getMedia().getAuteur()));
        return empruntService.findOne(id);
    }
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//@JsonView(AdherentViews.class)
	public Iterable<Emprunt> findAll() {
		//System.out.println("IL EST LA ");
        return empruntService.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Emprunt resource) {	
		empruntService.save(resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Emprunt update(@PathVariable Long id, @RequestBody @Valid Emprunt resource) {
		//System.out.println(resource.getNom()+" "+resource.getCotisation().getMontant());
		return empruntService.save(resource);
	}


	
}
