package adherent.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import adherent.model.Adherent;
import mediatic.dao.DAO;
import mediatic.dao.DatabaseHelper;

public class AdherentDao extends DAO<Adherent>{

	public AdherentDao() {
		super(Adherent.class);
  
	}
	
	
	public Adherent findAdherent(Long l)
	{
		EntityManager em = DatabaseHelper.createEntityManager();

		DatabaseHelper.beginTx(em);
		TypedQuery<Adherent> query = em.createQuery(
					       "select a " +
					       "from Adherent a " +
					       "inner join fetch a.cotisation c " +
					       "where a.id =:id ", Adherent.class);
					query.setParameter("id", l);
					
					Adherent a = query.getSingleResult();
					System.out.println(query.getSingleResult().getNom());
					DatabaseHelper.commitTxAndClose(em);
					
					
					return a;
	}
   
	public List<Adherent> findAdherentByNom(String nom){
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Adherent> query = em.createQuery(
			       "select a" +
			       "from Adherent a " +
			       "where a.nom like :nom ", Adherent.class);
			query.setParameter("nom","%"+nom+"%");
			
			List<Adherent> adherents = query.getResultList();		
			DatabaseHelper.commitTxAndClose(em);
			return adherents ;
	}

	
}
