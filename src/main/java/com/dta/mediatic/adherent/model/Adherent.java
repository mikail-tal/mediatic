
package com.dta.mediatic.adherent.model;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Formula;

import com.dta.mediatic.config.LocalDateDeserializer;
import com.dta.mediatic.config.LocalDateSerializer;
import com.dta.mediatic.cotisation.model.Cotisation;
import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table (name="adherent")
@SequenceGenerator(name="seq_adherent",sequenceName="seq_adherent",initialValue=1,allocationSize=1)
public class Adherent implements Comparable<Adherent>{

	@Id
	@GeneratedValue(generator = "seq_adherent")
	private Long id;
	

	@Column(name="id_char")
	private String idchar;
	

	@Column
	private String nom;
	
	
	
	
	@Column
	private String prenom;
	
	
	
	
	@Column(name="date_naissance")
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate dateNaissance;

	@Column
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private String email;
	
	
	
	@Column
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private String adresse;
	
	
	@Column
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private String cp;
	
	
	@Column
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private String ville;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private Cotisation cotisation;
	
	@OneToMany(mappedBy="adherent")
	@JsonSerialize(converter=AdherentEmpruntConverter.class)
	@JsonView(AdherentViews.AdherentViewDetails.class)
	private List<Emprunt> emprunt;


	@Column
	private long nbrMedia;
	
	@Column
	private String ajour;
	
	
	
	

	

	

	

	public Adherent(Long id, String nom, String prenom, LocalDate dateNaissance, Cotisation cotisation) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.cotisation = cotisation;
	}

	public Adherent(String nom, String prenom, LocalDate dateNaissaince, String email, String adresse, String cp,
			String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissaince;
		this.email = email;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
	}
	
	public Adherent(int id) {
		super();
		this.id = Long.valueOf(id);
	}
	
	public Adherent() {

	}
	
	public Long getId() {
		return id;
	}


	

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdChar() {
		return idchar;
	}

	public void setIdchar(String idChar) {
		this.idchar = idChar;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissaince) {
		this.dateNaissance = dateNaissaince;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Cotisation getCotisation() {
		return cotisation;
	}

	public void setCotisation(Cotisation cotisation) {
		this.cotisation = cotisation;
	}

	public List<Emprunt> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<Emprunt> emprunt) {
		this.emprunt = emprunt;
	}
	/*@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	public LocalDate getDateFinAbonnement(){
		return getCotisation().getDatePaiment().plusYears(1);
		
		
	}*/
	/*public Integer getAge(){
		
		
		return Period.between(getDateNaissaince(), LocalDate.now()).getYears();
	}
	*/
	
	/*public boolean abonnementIsValide(){
		return LocalDate.now().compareTo(getDateFinAbonnement()) < 0;
		
	}*/
	

	

	public long getNbrMedia() {
		return this.nbrMedia;//this.emprunt.stream().filter(distinctByKey(e->e.getMedia().getTitre())).count();
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	public void incrementMedia() {
		this.nbrMedia ++;
	}
	public void decrementMedia() {
		this.nbrMedia --;
	}
	public String getAjour() {
		if(cotisation.getDatePaiement()==null) {
			return "NON";
		}
		
		return cotisation.getDateFinAbonnement().isBefore(LocalDate.now())?"NON":"OUI";
	}

	public void setAjour(String ajour) {
		this.ajour = ajour;
	}

	@Override
	public int compareTo(Adherent o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}


