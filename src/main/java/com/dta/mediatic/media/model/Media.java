package com.dta.mediatic.media.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "media")
@SequenceGenerator(name = "seq_media", sequenceName = "seq_media", initialValue = 1, allocationSize = 1)
public class Media implements Comparable<Media> {

	@Id
	@GeneratedValue(generator = "seq_media")
	private Long id;

	@Column
	private String titre;

	@Column
	private String auteur;

	@Enumerated(EnumType.STRING)
	@JsonDeserialize(using = EnumDeserializer.class)
	private TypeMedia type;

	@OneToMany(mappedBy = "media")
	@JsonSerialize(converter = MediaEmpruntConverter.class)
	@JsonView(MediaViews.MediaView.class)
	private List<Emprunt> emprunt;

	@OneToOne
	@JsonSerialize(using = EmpruntSerializerMedia.class)
	private Emprunt empruntEnCours;

	public Media(Long id, String titre, String auteur, TypeMedia type) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
	}

	public TypeMedia getType() {
		return type;
	}

	public Media(int id) {
		super();
		this.id = Long.valueOf(id);
	}

	public void setType(TypeMedia type) {
		this.type = type;
	}

	public Media(String titre, String auteur, TypeMedia media) {
		this.titre = titre;
		this.auteur = auteur;
		this.type = media;
	}

	public Media() {

	}

	public Long getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public List<Emprunt> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<Emprunt> emprunt) {
		this.emprunt = emprunt;
	}

	public Emprunt getEmpruntEnCours() {
		return empruntEnCours;
	}

	public void setEmpruntEnCours(Emprunt empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || getClass() != obj.getClass())
			return false;

		else if (this.getTitre().equals(((Media) obj).getTitre()) && this.getAuteur().equals(((Media) obj).getAuteur())
				&& this.getType().equals(((Media) obj).getType()))
			return true;

		return false;
	}

	@Override
	public int compareTo(Media o) {
		// TODO Auto-generated method stub
		return this.getTitre().compareToIgnoreCase(o.getTitre());
	}

}
