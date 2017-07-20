package emprunt.service;

import emprunt.dao.EmpruntDao;
import emprunt.model.Emprunt;
import media.model.TypeMedia;
import media.service.MediaService;

public class EmpruntService {
	private static EmpruntService empruntService;
	
	public static EmpruntService getInstance(){
		if(empruntService==null){
			empruntService=new EmpruntService();
			
		}
		return empruntService;
	}
	
	public EmpruntDao getEmpruntDao() {
		return EmpruntDao.getInstance();
	}

	private EmpruntService(){
		
	}
	
	public void create(Emprunt emprunt){
		assignDateRetour(emprunt);
		getEmpruntDao().create(emprunt);
	}
	
	public void assignDateRetour(Emprunt emprunt){
		TypeMedia t=emprunt.getMedia().getType();
		if(t.equals(TypeMedia.CD) || t.equals(t.equals(TypeMedia.DVD))){
			emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(15));
		}else {
			emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(30));
		}
		
		
		
	}
	public void emprunter(Emprunt emprunt){
		if(emprunt.getMedia().getEmpruntEnCours()==null){
			create(emprunt);
			emprunt.getMedia().setEmpruntEnCours(emprunt);
			MediaService.getInstance().update(emprunt.getMedia());
		}
		
		
		
	}

}
