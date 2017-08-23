package com.dta.mediatic.media.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;
import com.dta.mediatic.media.service.DynamicMediaComparator;
import com.dta.mediatic.mediatic.dao.DAO;
import com.dta.mediatic.mediatic.dao.DatabaseHelper;

public class MediaDao extends DAO<Media> {
	private  List<Media>medias;
	private static MediaDao mediaDao;
	
	public static MediaDao getInstance(){
		if(mediaDao==null){
			mediaDao=new MediaDao();
		
		}
		return mediaDao;
	}

	private MediaDao() {
		super(Media.class);
	}

	public Media findWithEmprunt(Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("select m " + "from Media m " + "left join fetch m.emprunt e "
				+ "left join fetch e.adherent a " + "where m.id =:id ", Media.class);
		query.setParameter("id", id);
		Media media = query.getSingleResult();
		DatabaseHelper.commitTxAndClose(em);

		return media;
	}

	public List<Media> findMediasEmpruntes() {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("select m " + "from Media m " + "inner join fetch m.emprunt e ",
				Media.class);
		medias = query.getResultList();		
		DatabaseHelper.commitTxAndClose(em);

		return medias;
	}
	
	public List<Media> findMediaByTitre(String titre){
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT distinct m from Media m "
				+ "left join fetch m.empruntEnCours e "
				+ "left join fetch e.adherent a "
				+ "where m.titre like :titre",
				Media.class);
		query.setParameter("titre", "%"+titre+"%");
		medias = query.getResultList();		
		Collections.sort(medias);
		//medias.forEach(media->System.out.println(media.getTitre()));
		DatabaseHelper.commitTxAndClose(em);

		
		
		return medias;
	}
	public List<Media> findMediaByAuteur(String auteur){
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT distinct m from Media m "
				+ "left join fetch m.empruntEnCours e "
				+ "left join fetch e.adherent a "
				+ "where m.auteur like :auteur",
				Media.class);
		query.setParameter("auteur", "%"+auteur+"%");
		medias = query.getResultList();	
		Collections.sort(medias);
		DatabaseHelper.commitTxAndClose(em);
		

		
		return medias;
		
	}
	public List<Media>findMediaByTypeMedia(String typeMedia){
		TypeMedia type = null;
		for (TypeMedia t : TypeMedia.values()) {
			if(t.name().equals(typeMedia)){
				type=t;
			}
		}
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT distinct m from Media m "
				+ "left join fetch m.empruntEnCours e "
				+ "left join fetch e.adherent a "
				+ "where m.type=:type",
				Media.class);
		query.setParameter("type", type);
		medias = query.getResultList();		
		Collections.sort(medias);
		DatabaseHelper.commitTxAndClose(em);


		
		return medias;
	}
	public List<Media> sortMediaBy(Integer number){
		
		
		
		
		
				Collections.sort(medias, DynamicMediaComparator.getInstance(number));
			
		//medias.forEach(media->System.out.println("TEST"+media.getTitre()));
		return medias;
	}
	/*public List<Media> sortMediaByAuteur(String set){
		if(set=="ASC"){
			medias.sort((Media m1,Media m2)->m1.getAuteur().compareToIgnoreCase(m2.getAuteur()));

		}
		if(set=="DESC"){
			medias.sort((Media m1,Media m2)->m2.getAuteur().compareToIgnoreCase(m1.getAuteur()));

		}
		return medias;
	}
	public List<Media> sortMediaByType(String set){
		if(set=="ASC"){
			medias.sort((Media m1,Media m2)->m1.getType().compareTo(m2.getType()));

		}
		if(set=="DESC"){
			medias.sort((Media m1,Media m2)->m2.getType().compareTo(m1.getType()));

		}
		return medias;
	}
	
	public List<Media> sortMediaByAdherent(String set){
		if(set=="ASC"){
			medias.sort((Media m1,Media m2)->m1.getEmpruntEnCours().getAdherent().getNom().
					compareToIgnoreCase(m2.getEmpruntEnCours().getAdherent()));

		}
		if(set=="DESC"){
			medias.sort((Media m1,Media m2)->m2.getType().compareTo(m1.getType()));

		}
		return medias;
	}
	*/
	

}
