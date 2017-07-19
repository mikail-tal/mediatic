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
					
					/*List <Adherent> adherents = query.getResultList();
					
					for(Adherent a: adherents)
					{
						System.out.println(a.getNom()+" "+a.getPrenom());
					}
					*/
					DatabaseHelper.commitTxAndClose(em);
					
					
					return a;
	}
	
	

}
