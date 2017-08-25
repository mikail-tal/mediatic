package com.dta.mediatic.authentification.utilAuthentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dta.mediatic.user.model.User;
import com.dta.mediatic.user.service.UserService;



@Component
public class RestAuthenticationSuccessHandler 
extends SimpleUrlAuthenticationSuccessHandler {
 
 @Autowired
 private UserService userService;
 
 @Override
 public void onAuthenticationSuccess(HttpServletRequest request, 
  HttpServletResponse response, Authentication authentication)
 throws ServletException, IOException {
  User user = null;
try {
	user = userService.findByLogin(authentication.getName());
	System.out.println("ABCD");
} catch (NotFoundException e) {
	
	e.printStackTrace();
}
  SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
 }
}