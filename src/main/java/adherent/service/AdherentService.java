package adherent.service;

import adherent.dao.AdherentDao;
import adherent.model.Adherent;

public class AdherentService {
	static AdherentDao adherentDao;
	
	public AdherentDao getInstance(){
		if(adherentDao==null){
			adherentDao= new AdherentDao();
		}
		return adherentDao;
	}
	
	public void create(Adherent adherent){
		adherentDao.create(adherent);		
	}
	

}
