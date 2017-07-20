package adherent.service;

import adherent.dao.AdherentDao;
import adherent.model.Adherent;

public class AdherentService {
	static AdherentDao adherentDao;
	
	public static AdherentDao getInstance(){
		if(adherentDao==null){
			adherentDao= AdherentDao.getInstance();
		}
		return adherentDao;
	}
	
	public void create(Adherent adherent){
		adherentDao.create(adherent);		
	}
	
	
	

}
