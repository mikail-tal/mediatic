package emprunt.dao;

import emprunt.model.Emprunt;
import mediatic.dao.DAO;

public class EmpruntDao extends DAO<Emprunt>{
	private static EmpruntDao empruntDao;
		
	
	public static EmpruntDao getInstance(){
		if(empruntDao==null){
			empruntDao=new EmpruntDao();
		}
		return empruntDao;
	}
	private EmpruntDao() {
		super(Emprunt.class);
	}

	

	
	

}
