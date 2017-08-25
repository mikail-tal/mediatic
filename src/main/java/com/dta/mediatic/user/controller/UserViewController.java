package com.dta.mediatic.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dta.mediatic.user.model.User;
import com.dta.mediatic.user.service.UserService;


@Controller
@RequestMapping("/public/users")
@SessionAttributes("user")
public class UserViewController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView display() {
		User user = new User();
		ModelAndView mv = new ModelAndView("user");
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute User user, BindingResult br, Model model) {
		model.addAttribute("test", "helloWorld");
		if (br.hasErrors()) {
			return "user";
		}
		userService.create(user);
		return "update_user_ok";
	}

}