package com.dta.mediatic.user.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.dta.mediatic.adherent.model.AdherentViews;
import com.dta.mediatic.user.model.User;
import com.dta.mediatic.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/mediatic/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public User findById(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }
		
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
        return userService.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
	
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid User resource) {
		userService.create(resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public User update(@PathVariable Long id, @RequestBody @Valid User resource) {
		return userService.update(id, resource);
	}
	@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(value="/filter" ,method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<User> findSorted(
			@RequestParam(value="field", required =false) String field,
			@RequestParam(value="order", required =false) String order,
			@RequestParam(value="page",defaultValue="0" ,required=false) int page,
			@RequestParam(value="size",defaultValue="10",required=false) int size) {
		
		
		//adherentService.orderBy(field, order, new PageRequest(page, size)).getContent().forEach(a->System.out.println(a.getNbrMedia()));
			
		
		
		return userService.findAllByOrderByNameAsc(new PageRequest(page, size));
				
	}
	//@JsonView(AdherentViews.AdherentView.class)
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public User findByName(
			@RequestParam(value="login", required =false) String login) {
		
		
		//adherentService.orderBy(field, order, new PageRequest(page, size)).getContent().forEach(a->System.out.println(a.getNbrMedia()));
			Optional<User> opt=userService.findOneByLogin(login);
			System.out.println("UserController.findByName()");
			if(opt.isPresent()) {
				return opt.get();
			}else {
				return null;
			}
		
		
		//userService.findAllByOrderByNameAsc(new PageRequest(page, size));
				
	}
	
}