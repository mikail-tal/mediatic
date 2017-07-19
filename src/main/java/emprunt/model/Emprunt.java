package emprunt.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import adherent.model.Adherent;
import media.model.Media;
@Entity
@Table (name="emprunt")
@SequenceGenerator(name="seq_emprunt",sequenceName="seq_emprunt",initialValue=1,allocationSize=1)
public class Emprunt {
	@Id
	@GeneratedValue(generator="seq_emprunt")
	private Long id;
	@ManyToOne
	private Media media;
	@ManyToOne
	private Adherent adherent;
	private LocalDate dateEmprunt;
	private LocalDate dateRetourEffectif;
	private LocalDate dateRetourPrevue;
	public Emprunt(Media media, Adherent adherent, LocalDate dateEmprunt, LocalDate dateRetourEffectif,
			LocalDate dateRetourPrevue) {
		this.media = media;
		this.adherent = adherent;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourEffectif = dateRetourEffectif;
		this.dateRetourPrevue = dateRetourPrevue;
	}
	public Emprunt() {
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
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public LocalDate getDateRetourEffectif() {
		return dateRetourEffectif;
	}
	public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
		this.dateRetourEffectif = dateRetourEffectif;
	}
	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}
	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	
	

}
