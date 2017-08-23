package com.dta.mediatic.cotisation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.config.LocalDateDeserializer;
import com.dta.mediatic.config.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
@Table (name="cotisation")
@SequenceGenerator(name="seq_cotisation",sequenceName="seq_cotisation",initialValue=1,allocationSize=1)
public class Cotisation {
    @Id
    @GeneratedValue(generator="seq_cotisation")
	private Long id;
    @Column
	private Long montant;
    
    @JsonSerialize(using = LocalDateSerializer.class) 
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @Column(name="date_paiement")
	private LocalDate datePaiement;
    
    @JsonSerialize(using = LocalDateSerializer.class) 
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @Column(name="date_fin_abonnement")
    private LocalDate dateFinAbonnement;
    
    @OneToOne(mappedBy = "cotisation")
	   private Adherent adherent;

	public Cotisation(Long montant, LocalDate datePaiment) {
		this.montant = montant;
		this.datePaiement = datePaiment;
	}

	public Cotisation() {

	}

	public Long getId() {
		return id;
	}

	public Long getMontant() {
		return montant;
	}
	public LocalDate getDatePaiement() {
		return datePaiement;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMontant(Long montant) {
		this.montant = montant;
	}

	public void setDatePaiement(LocalDate datePaiment) {
		this.datePaiement = datePaiment;
	}

	public LocalDate getDateFinAbonnement() {
		return dateFinAbonnement;
	}

	public void setDateFinAbonnement(LocalDate dateFinAbonnement) {
		this.dateFinAbonnement = dateFinAbonnement;
	}
}
