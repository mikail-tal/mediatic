package com.dta.mediatic.emprunt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.config.LocalDateDeserializer;
import com.dta.mediatic.config.LocalDateSerializer;
import com.dta.mediatic.media.model.Media;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
@Table (name="emprunt")
@SequenceGenerator(name="seq_emprunt",sequenceName="seq_emprunt",initialValue=1,allocationSize=1)
public class Emprunt {
	@Id
	@GeneratedValue(generator="seq_emprunt")
	private Long id;
	@ManyToOne
	
	@Cascade(CascadeType.MERGE)
	//@Column(name="media")
	private Media media;
	@ManyToOne
	@Cascade(CascadeType.MERGE)
	
	private Adherent adherent;
	@Column(name="date_emprunt")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	private LocalDate dateEmprunt;
	@Column(name="date_retour_effectif")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	private LocalDate dateRetourEffectif;
	@Column(name="date_retour_prevue")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)

	private LocalDate dateRetourPrevue;
	public Emprunt(Media media, Adherent adherent, LocalDate dateEmprunt) {
		this.media = media;
		this.adherent = adherent;
		this.dateEmprunt = dateEmprunt;
		
	}
	
	public Emprunt() {
	}
	public Emprunt(Long id, Media media, LocalDate dateEmprunt, LocalDate dateRetourEffectif,
			LocalDate dateRetourPrevue) {
		super();
		this.id = id;
		this.media = media;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourEffectif = dateRetourEffectif;
		this.dateRetourPrevue = dateRetourPrevue;
	}

	

	public Emprunt(Long id, Adherent adherent) {
		super();
		this.id = id;
		this.adherent = adherent;
	}

	public Emprunt(Long id, Media media) {
		super();
		this.id = id;
		this.media = media;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public Adherent getAdherent() {
		return adherent;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	@JsonSerialize(using = LocalDateSerializer.class)  
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	public LocalDate getDateRetourEffectif() {
		return dateRetourEffectif;
	}
	@JsonSerialize(using = LocalDateSerializer.class)  
	public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
		this.dateRetourEffectif = dateRetourEffectif;
	}
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}
	@JsonSerialize(using = LocalDateSerializer.class)  
	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	
	

}
