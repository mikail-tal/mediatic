package com.dta.mediatic.roleConnexion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
@Entity
public class Role {
    @Id
    @GeneratedValue
	private Long id ;
	private String name ;
	
	public Role(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
