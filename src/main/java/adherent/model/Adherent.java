
package adherent.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cotisation.model.Cotisation;
import emprunt.model.Emprunt;

@Entity
@Table (name="adherent")
@SequenceGenerator(name="seq_adherent",sequenceName="seq_adherent",initialValue=1,allocationSize=1)
public class Adherent implements Comparable<Adherent>{

	@Id
	@GeneratedValue(generator = "seq_adherent")
	private Long id;

	@Column
	private String nom;
	@Column
	private String prenom;

	@Column
	private LocalDate dateNaissaince;
	
	

	@Column
	private String email;
	@Column
	private String adresse;
	@Column
	private String cp;
	@Column
	private String ville;

	@OneToOne
	private Cotisation cotisation;
	
	@OneToMany
	private List<Emprunt> emprunt;


	public Adherent(String nom, String prenom, LocalDate dateNaissaince, String email, String adresse, String cp,
			String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissaince = dateNaissaince;
		this.email = email;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
	}
	
	public Adherent() {

	}
	
	public Long getId() {
		return id;
	}


	

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDateNaissaince() {
		return dateNaissaince;
	}

	public void setDateNaissaince(LocalDate dateNaissaince) {
		this.dateNaissaince = dateNaissaince;
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
	
	public LocalDate getDateFinAbonnement(){
		return getCotisation().getDatePaiment().plusYears(1);
		
		
	}
	public Integer getAge(){
		
		
		return Period.between(getDateNaissaince(), LocalDate.now()).getYears();
	}
	
	
	public boolean abonnementIsValide(){
		return LocalDate.now().compareTo(getDateFinAbonnement()) < 0;
		
	}
	

	@Override
	public int compareTo(Adherent o) {
		int result=this.getNom().compareToIgnoreCase(o.getNom());
		if(result==0) return this.getPrenom().compareToIgnoreCase(o.getPrenom());
		else return result;
		
		
	}

}


