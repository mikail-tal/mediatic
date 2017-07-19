package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table (name="cotisation")
@SequenceGenerator(name="seq_cotisation",sequenceName="seq_cotisation",initialValue=1,allocationSize=1)
public class Cotisation {
    @Id
    @GeneratedValue(generator="seq_cotisation")
	private Long id;
    @Column
	private Long montant;
    @Column
	private LocalDate datePaiment;

	public Cotisation(Long montant, LocalDate datePaiment) {
		this.montant = montant;
		this.datePaiment = datePaiment;
	}

	public Cotisation() {

	}

	public Long getId() {
		return id;
	}

	public Long getMontant() {
		return montant;
	}

	public LocalDate getDatePaiment() {
		return datePaiment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMontant(Long montant) {
		this.montant = montant;
	}

	public void setDatePaiment(LocalDate datePaiment) {
		this.datePaiment = datePaiment;
	}
}
