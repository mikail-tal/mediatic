package com.dta.mediatic.user.utilAuthentification;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import com.dta.mediatic.user.model.User;



public class SecurityUtils {

	public static void sendResponse(HttpServletResponse response, int httpServletResponse, User user) {
		response.setStatus(httpServletResponse);
		
	}

	public static void sendError(HttpServletResponse response, AuthenticationException exception, int scUnauthorized,
			String string) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

	public static void sendError(HttpServletResponse response, AccessDeniedException exception, int scForbidden,
			String string) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
	}

}
