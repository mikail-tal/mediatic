package emprunt.service;

import emprunt.dao.EmpruntDao;
import emprunt.model.Emprunt;
import media.model.TypeMedia;
import media.service.MediaService;

public class EmpruntService {
	
	static EmpruntDao empruntDao;
	public static EmpruntDao getInstance(){
		if(empruntDao==null){
			empruntDao=new EmpruntDao();
		}
		return empruntDao;
	}
	
	public void create(Emprunt emprunt){
		assignDateRetour(emprunt);
		empruntDao.create(emprunt);
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
		create(emprunt);
		
			emprunt.getMedia().setEmpruntEnCours(emprunt);
			MediaService.getInstance().update(emprunt.getMedia());
		
		
		
	}

}
