package com.dta.mediatic.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user_")
public class User implements Serializable {

	private static final long serialVersionUID = -6256366557738084870L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotBlank
    private String login;

    @NotBlank
    private String password;

	@NotBlank
	@Length(max = 100)
	private String name;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="credentials", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="credential")
	
	private List<String> credentials = new ArrayList<>();
	
	public User() {
		credentials.add("ROLE_BASIC");
	}

	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<String> credentials) {
		this.credentials = credentials;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + " ,login=" + login + ", password=" + password + "]";
    }
}

