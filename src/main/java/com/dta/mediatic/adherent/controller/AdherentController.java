package com.dta.mediatic.adherent.controller;

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

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.adherent.model.AdherentViews;
import com.dta.mediatic.adherent.service.AdherentService;
import com.fasterxml.jackson.annotation.JsonView;




@RestController
@RequestMapping("/mediatic/adherent")

public class AdherentController {
	@Autowired
	private AdherentService adherentService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Adherent findById(@PathVariable Long id){
        return adherentService.findOne(id);
    }
	@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Adherent> findAll() {
        return adherentService.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Adherent resource) {	
		System.out.println(resource.getDateNaissance());
		adherentService.save(resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Adherent update(@PathVariable Long id, @RequestBody @Valid Adherent resource) {
		System.out.println(resource.getNom()+" "+resource.getCotisation().getMontant());
		return adherentService.save(resource);
	}

}
