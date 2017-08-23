package com.dta.mediatic.emprunt.dao;

import com.dta.mediatic.emprunt.model.Emprunt;
import com.dta.mediatic.mediatic.dao.DAO;

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
