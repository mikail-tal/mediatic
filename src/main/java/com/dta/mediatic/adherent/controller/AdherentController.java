package com.dta.mediatic.adherent.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.adherent.model.AdherentViews;
import com.dta.mediatic.adherent.service.AdherentService;

import com.fasterxml.jackson.annotation.JsonView;




@RestController
@RequestMapping("/mediatic/adherent")

public class AdherentController{
	/**
	 * 
	 */
	
	@Autowired
	private AdherentService adherentService;
	
	
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Adherent findById(@PathVariable Long id){
        return adherentService.findOne(id);
    }
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Adherent resource) {	
		//System.out.println(resource.getDateNaissance());
		adherentService.save(resource);
	}
	@RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Adherent update(@PathVariable Long id, @RequestBody @Valid Adherent resource) {
		return adherentService.save(resource);
	}
	
	@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Adherent> findAllBy(@RequestParam(value="id", required =false) String id,
									 @RequestParam(value="nom", required =false) String nom,
									 @RequestParam(value="page",defaultValue="0" ,required=false) int page,
									 @RequestParam(value="size",defaultValue="10",required=false) int size) {
		if(!("".equals(null)|| id==null) && !("".equals(nom) || nom==null) ) {		
			return adherentService.findByIdcharStartsWithAndNomIgnoreCaseContaining(id, nom, new PageRequest(page, size));
		}else if(!( id==null || "".equals(null))) {	
			return adherentService.findByIdcharStartsWith(id, new PageRequest(page, size));
		}else if(!("".equals(nom) || nom==null) ){
			return adherentService.findByNomIgnoreCaseContaining(nom, new PageRequest(page, size));
		}
		return adherentService.findAllOrderByNomAndPrenomAsc(new PageRequest(page, size));
        
    }
	@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(value="/search" ,method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Adherent> findByIdOrName(
			@RequestParam(value="keyword", required =false) String keyword,
			@RequestParam(value="page",defaultValue="0" ,required=false) int page,
			@RequestParam(value="size",defaultValue="10",required=false) int size) {
		
		if(keyword==null | "".equals(keyword)) {
			return adherentService.findAllOrderByNomAndPrenomAsc(new PageRequest(page, size));
		}else if( keyword!=null | "".equals(keyword)){
			return adherentService.findByIdcharStartsWithOrNomIgnoreCaseContaining(keyword, keyword, new PageRequest(page, size));
		}
		return adherentService.findAllOrderByNomAndPrenomAsc(new PageRequest(page, size));
	}
	
	@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(value="/filter" ,method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Adherent> findSorted(
			@RequestParam(value="field", required =false) String field,
			@RequestParam(value="order", required =false) String order,
			@RequestParam(value="page",defaultValue="0" ,required=false) int page,
			@RequestParam(value="size",defaultValue="10",required=false) int size) {
		
		
		//adherentService.orderBy(field, order, new PageRequest(page, size)).getContent().forEach(a->System.out.println(a.getNbrMedia()));
			
		
		
		return adherentService.orderBy(field, order, new PageRequest(page, size));
				
	}
}
