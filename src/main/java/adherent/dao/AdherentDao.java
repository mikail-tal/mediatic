package adherent.dao;


import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import adherent.model.Adherent;
import adherent.service.DynamicAdherentComparator;
import mediatic.dao.DAO;
import mediatic.dao.DatabaseHelper;

public class AdherentDao extends DAO<Adherent>{
	private List<Adherent> adherents;
	private static AdherentDao adherentDao;
	
	public static AdherentDao getInstance(){
		if(adherentDao==null){
			adherentDao=new AdherentDao();
		}
		return adherentDao;
	}

	private AdherentDao() {
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
					Collections.sort(adherents);
					
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
			
			 adherents = query.getResultList();		
			 Collections.sort(adherents);
			DatabaseHelper.commitTxAndClose(em);
			Collections.sort(adherents);
			return adherents ;
	}
	
	public List<Adherent>findAdherentByStartId(Integer startId){
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Adherent> query = em.createQuery(
			       "select a" +
			       "from Adherent a " +
			       "where a.id like :startId ", Adherent.class);
			query.setParameter("startId",startId+"%");
			
			adherents = query.getResultList();	
			Collections.sort(adherents);
			DatabaseHelper.commitTxAndClose(em);
		return adherents;
	}
	
	
	public List<Adherent> sortAdherentBy(Integer number){
		Collections.sort(adherents, DynamicAdherentComparator.getInstance(number));

		return adherents;
	}

	
}
