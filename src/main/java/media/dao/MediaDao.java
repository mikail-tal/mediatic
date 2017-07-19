package media.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import media.model.Media;
import mediatic.dao.DAO;
import mediatic.dao.DatabaseHelper;

public class MediaDao extends DAO<Media> {

	public MediaDao() {
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
		List<Media> medias = query.getResultList();		
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
		List<Media> medias = query.getResultList();		
		DatabaseHelper.commitTxAndClose(em);


		
		return medias;
	}

}
