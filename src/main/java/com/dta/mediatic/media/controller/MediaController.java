package com.dta.mediatic.media.controller;

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

import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.MediaViews;
import com.dta.mediatic.media.service.MediaService;
import com.fasterxml.jackson.annotation.JsonView;
@RestController
@RequestMapping("/mediatic/media")
public class MediaController {
	

	
	
	@Autowired
	private MediaService mediaService;
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(value = "{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//@JsonView(AdherentViews.class)
	public Media findById(@PathVariable Long id){
	//	adherentService.findById(id).getEmprunt().forEach(e->System.out.println(e.getMedia().getAuteur()));
        return mediaService.findOne(id);
    }
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//@JsonView(AdherentViews.class)
	public Iterable<Media> findAll() {
		//System.out.println("IL EST LA ");
        return mediaService.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Media resource) {	
		mediaService.save(resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Media update(@PathVariable Long id, @RequestBody @Valid Media resource) {
		//System.out.println(resource.getNom()+" "+resource.getCotisation().getMontant());
		return mediaService.save(resource);
	}


	
}
