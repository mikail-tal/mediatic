package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="media")
@SequenceGenerator(name="seq_media",sequenceName="seq_media",initialValue=1,allocationSize=1)
public class Media {
    @Id
    @GeneratedValue(generator="seq_media")
	private Long id;
	@Column
	private String titre;
	@Column
	private String auteur;
	@Column
	private TypeMedia media;

	public Media(String titre, String auteur, TypeMedia media) {
		this.titre = titre;
		this.auteur = auteur;
		this.media = media;
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

	public TypeMedia getMedia() {
		return media;
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

	public void setMedia(TypeMedia media) {
		this.media = media;
	}
}
