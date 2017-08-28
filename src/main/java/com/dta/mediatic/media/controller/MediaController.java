package com.dta.mediatic.media.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dta.mediatic.adherent.model.AdherentViews;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.MediaViews;
import com.dta.mediatic.media.model.TypeMedia;
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
	/*@JsonView(MediaViews.MediaView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//@JsonView(AdherentViews.class)
	public Iterable<Media> findAll() {
		//System.out.println("IL EST LA ");
        return mediaService.findAll();
    }*/
	
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
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Media> findAllBy(@RequestParam(value="titre", required =false) String titre,
									 @RequestParam(value="auteur", required =false) String auteur,
									 @RequestParam(value="type", required =false) String type,
									 @RequestParam(value="page",defaultValue="0" ,required=false) int page,
									 @RequestParam(value="size",defaultValue="10",required=false) int size) {
		if((("".equals(titre)) | (titre==null)) && 
				(("".equals(auteur))  | (auteur==null))
				& (("".equals(type)) | (type==null))) {	
			System.out.println("1");
			return mediaService.findAllByOrderByTitreAsc(new PageRequest(page, size));
		}else
			if(( titre==null | "".equals(titre)) ) {	
				if(type==null | "".equals(type)) {
					System.out.println("1");

					return mediaService.findByAuteurIgnoreCaseContainingOrderByTitreAsc(auteur, new PageRequest(page, size));
				}else{
					System.out.println("1");

					return mediaService.findByAuteurIgnoreCaseContainingAndTypeOrderByTitreAsc(auteur, TypeMedia.valueOf(type), new PageRequest(page, size));

				}
			
		}else
		if(("".equals(auteur) | auteur==null) ){
				if(type==null | "".equals(type)) {
					System.out.println("1");

					return mediaService.findByTitreIgnoreCaseContainingOrderByTitreAsc(titre, new PageRequest(page, size));
				}else {
					System.out.println("1");

					return mediaService.findByTitreIgnoreCaseContainingAndTypeOrderByTitreAsc(titre, TypeMedia.valueOf(type), new PageRequest(page, size));

				}
			
		}else
		if(("".equals(type)||type==null)) {
			if("".equals(auteur)||auteur==null) {
				System.out.println("1");

				return mediaService.findByTitreIgnoreCaseContainingOrderByTitreAsc(titre, new PageRequest(page, size));
			}else {
				System.out.println("1");

				return mediaService.findByAuteurIgnoreCaseContainingAndTitreIgnoreCaseContainingOrderByTitreAsc(auteur, titre, new PageRequest(page, size));
			}
		}
			return 	mediaService.findAllByOrderByTitreAsc(new PageRequest(page, size));
        
    }
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(value="/search" ,method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Media> findByIdOrName(
			@RequestParam(value="keyword", required =false) String keyword,
			@RequestParam(value="page",defaultValue="0" ,required=false) int page,
			@RequestParam(value="size",defaultValue="10",required=false) int size) {
		
		if(keyword==null | "".equals(keyword)) {
			System.out.println("NULL");
			return mediaService.findAllByOrderByTitreAsc(new PageRequest(page, size));
			
		}else if( keyword!=null & !("".equals(keyword))){
			System.out.println("NOT NULL");
			return mediaService.findByTitreIgnoreCaseContainingOrAuteurIgnoreCaseContainingOrderByTitreAsc
					(keyword,keyword, new PageRequest(page, size));
			
		}
		return mediaService.findAllByOrderByTitreAsc(new PageRequest(page, size));
	}
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(value="/filter" ,method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Media> findSorted(
			@RequestParam(value="field", required =false) String field,
			@RequestParam(value="order", required =false) String order,
			@RequestParam(value="page",defaultValue="0" ,required=false) int page,
			@RequestParam(value="size",defaultValue="10",required=false) int size) {
		
		
		//adherentService.orderBy(field, order, new PageRequest(page, size)).getContent().forEach(a->System.out.println(a.getNbrMedia()));
			
		
		
		return mediaService.orderBy(field, order, new PageRequest(page, size));
				
	}
	@JsonView(MediaViews.MediaView.class)
	@RequestMapping(value="/nonEmprunte",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Media> findAllNonEmpruntee(@RequestParam(value="titre", required =false) String titre,
									 @RequestParam(value="type", required =false) String type,
									 @RequestParam(value="page",defaultValue="0" ,required=false) int page,
									 @RequestParam(value="size",defaultValue="10",required=false) int size) {
		if(type==null | "".equals(type)) {
			return mediaService.findByTitreIgnoreCaseContainingAndEmpruntEncoursIsNull(titre,new PageRequest(page, size));
		}else {
			return mediaService.findByTitreIgnoreCaseContainingAndTypeAndEmpruntEnCoursIsNull(titre, TypeMedia.valueOf(type),new PageRequest(page, size));

		}

		
		
	}
	
	
}
