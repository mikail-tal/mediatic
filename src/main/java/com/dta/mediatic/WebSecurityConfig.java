package com.dta.mediatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.dta.mediatic.authentification.service.AuthenticationService;
import com.dta.mediatic.authentification.utilAuthentification.RestAccessDeniedHandler;
import com.dta.mediatic.authentification.utilAuthentification.RestAuthenticationEntryPoint;
import com.dta.mediatic.authentification.utilAuthentification.RestAuthenticationFailureHandler;
import com.dta.mediatic.authentification.utilAuthentification.RestAuthenticationSuccessHandler;
import com.dta.mediatic.user.model.User;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	RestAccessDeniedHandler restAccessDeniedHandler;
	@Autowired
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	@Autowired
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().and().authorizeRequests()

				.antMatchers("/public/app/**").permitAll()

				.anyRequest().permitAll()
				
				.and().exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).accessDeniedHandler(restAccessDeniedHandler)
				.and().formLogin()
				.loginProcessingUrl("/authenticate")
					.successHandler(restAuthenticationSuccessHandler).permitAll()
					.failureHandler(restAuthenticationFailureHandler)
					.usernameParameter("username")
					.passwordParameter("password").permitAll()
					
				.and().logout().logoutUrl("/logout")
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.permitAll()
				.and().httpBasic().and().csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService User=authenticationService;
		auth.userDetailsService(User).passwordEncoder(passwordEncoder());
		//auth.authenticationProvider(authenticationProvider());
		//auth.inMemoryAuthentication().withUser(User.).password("password").roles("USER");
		
		
		
		//auth.inMemoryAuthentication().
		System.out.println(auth.userDetailsService(authenticationService).getUserDetailsService());
		//auth.inMemoryAuthentication().withUser(username)
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(authenticationService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	
	 
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("ayoub").password("12345").roles("ADMIN");
    }*/
}
