package cotisation.service;

import cotisation.dao.CotisationDao;
import cotisation.model.Cotisation;

public class CotisationService {
	static CotisationDao cotisationDao;
	public CotisationDao getInstance(){
		if(cotisationDao==null){
			cotisationDao=new CotisationDao();
		}
		return cotisationDao;
	}
	
	public void create(Cotisation cotisation){
		cotisationDao.create(cotisation);
	}

}
