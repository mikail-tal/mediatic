package mediatic;

import java.time.LocalDate;

import adherent.dao.AdherentDao;
import adherent.model.Adherent;
import adherent.service.AdherentService;
import cotisation.dao.CotisationDao;
import cotisation.model.Cotisation;
import cotisation.service.CotisationService;
import emprunt.dao.EmpruntDao;
import emprunt.model.Emprunt;
import emprunt.service.EmpruntService;
import media.dao.MediaDao;
import media.model.Media;
import media.model.TypeMedia;
import media.service.MediaService;

public class Main {
	
	public static void main(String[] args) {
		
		
		//EmpruntDao empruntDao=new EmpruntDao();
		//CotisationDao cotisationDao=new CotisationDao();
		//AdherentDao adherentDao=new AdherentDao();
		//MediaDao mediaDao=new MediaDao();
		Cotisation cotisation=new Cotisation(500l,LocalDate.of(2017, 7, 10));
		Adherent adherent=new Adherent("BENGHAL", "AYOUB", LocalDate.of(1989, 8, 14), "ayoub.benghal@gmail.com", "18 RUE MIRON", "06000", "NICE");
		
		adherent.setCotisation(cotisation);
		//AdherentDao adherent1 = new AdherentDao();
		
		
		Media javaNul=new Media("JAVA POUR LES NULS", "ABDEL ET DIOGO", TypeMedia.LIVRE);
		Media oracleNul=new Media("ORACLE POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.DVD);
		Media sqlNul=new Media("SQL POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.CD);
		
		/*mediaDao.create(javaNul);
		mediaDao.create(oracleNul);
		mediaDao.create(sqlNul);
		cotisationDao.create(cotisation);
		adherentDao.create(adherent);*/
		
		Emprunt emprunt1=new Emprunt(sqlNul, adherent,LocalDate.now());
		Emprunt emprunt2=new Emprunt(oracleNul, adherent,LocalDate.now());
		/*empruntDao.create(emprunt1);
		empruntDao.create(emprunt2);*/
		
		//adherent1.findAdherent(1l);
		
		
		/*Adherent ado = adherent1.findAdherent(adherent.getId());
		System.out.println(ado.getNom()+" "+ado.getPrenom());
	*/
		
		CotisationService.getInstance().create(cotisation);
		AdherentService.getInstance().create(adherent);
		MediaService.getInstance().create(sqlNul);
		MediaService.getInstance().create(oracleNul);
		MediaService.getInstance().create(javaNul);
		EmpruntService empruntService=new EmpruntService();
		empruntService.getInstance();
		empruntService.emprunter(emprunt1);
		empruntService.emprunter(emprunt2);
		//EmpruntService.getInstance().create(emprunt2);
		MediaDao mediaDao=new MediaDao();
		sqlNul.setAuteur("TEST");
		MediaService.getInstance().update(sqlNul);
		System.out.println(mediaDao.findMediaByTitre("SQL").size());
		for (Media media : mediaDao.findMediaByTitre("SQL")) {
			if(media.getEmpruntEnCours()==null){
				System.out.println(media.getTitre()+" "+media.getAuteur()+" "
						);
				
			}else{
				System.out.println(media.getTitre()+" "+media.getTitre()+" "+
						media.getEmpruntEnCours().getAdherent().getNom()+" "+
									media.getEmpruntEnCours().getDateRetourPrevue());
			}
		}
		
		
		//empruntDao.create(new Emprunt(new Media(titre, auteur, media), adherent, dateEmprunt, dateRetourEffectif, dateRetourPrevue));
	}
		
	
		
		
}
