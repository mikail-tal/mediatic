package com.dta.mediatic.mediatic;

<<<<<<< HEAD
import java.time.LocalDate;

import com.dta.mediatic.adherent.model.Adherent;
import com.dta.mediatic.adherent.service.AdherentService;
import com.dta.mediatic.cotisation.model.Cotisation;
import com.dta.mediatic.cotisation.service.CotisationService;
import com.dta.mediatic.emprunt.model.Emprunt;
import com.dta.mediatic.emprunt.service.EmpruntService;
import com.dta.mediatic.garbage.MediaDao;
import com.dta.mediatic.media.model.Media;
import com.dta.mediatic.media.model.TypeMedia;
import com.dta.mediatic.media.service.MediaService;

=======
>>>>>>> branch 'SPRING_BOOT' of https://github.com/mikail-tal/mediatic.git
public class Main {
	
	public static void main(String[] args) {/*
		
		
		
		
		Cotisation cotisation=new Cotisation(500l,LocalDate.of(2017, 7, 10));
		Cotisation cotisation2=new Cotisation(400l, LocalDate.of(2017, 6, 12));
		Cotisation cotisation3=new Cotisation(500l, LocalDate.of(2017, 9, 6));
		Adherent adherent=new Adherent("BENGHAL", "AYOUB", LocalDate.of(1989, 8, 14), "ayoub.benghal@gmail.com", "18 RUE MIRON", "06000", "NICE");
		Adherent adherent1=new Adherent("Biogo", "monteiro", LocalDate.of(1991,4,4), "diogo@femme.com", "5 rue des etoiles", "12000", "lune");
		Adherent adherent2=new Adherent("BENGHAL", "ABYOUB", LocalDate.of(1989, 8, 14), "ayoub.benghal@gmail.com", "9 BD PAPE JEAN", "06000", "NICE");
		
		adherent.setCotisation(cotisation);
		adherent1.setCotisation(cotisation2);
		adherent2.setCotisation(cotisation3);
		//AdherentDao adherent1 = new AdherentDao();
        
		
		Media sqlNul=new Media("SQL POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.CD);
		Media javaNul=new Media("JAVA POUR LES NULS DE SQL", "ABDEL ET DIOGO", TypeMedia.DVD);
		Media oracleNul=new Media("ORACLE POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.LIVRE);
		
		
		
		
		
		Emprunt emprunt1=new Emprunt(sqlNul, adherent, LocalDate.now());
		Emprunt emprunt2=new Emprunt(oracleNul, adherent1, LocalDate.of(2017, 6, 20));
		Emprunt emprunt3=new Emprunt(sqlNul, adherent2, LocalDate.now());
		empruntDao.create(emprunt1);
		empruntDao.create(emprunt2);
		
		//adherent1.findAdherent(1l);
		
		
		Adherent ado = adherent1.findAdherent(adherent.getId());
		/*		
		AdherentDao d1 = new AdherentDao();
		Adherent ado = d1.findAdherent(adherent.getId());
		System.out.println(ado.getNom()+" "+ado.getPrenom());
	
	   
		
		CotisationService.getInstance().create(cotisation);
		CotisationService.getInstance().create(cotisation2);
		CotisationService.getInstance().create(cotisation3);
		AdherentService.getInstance().create(adherent);
		AdherentService.getInstance().create(adherent1);
		AdherentService.getInstance().create(adherent2);
		MediaService.getInstance().create(sqlNul);
		MediaService.getInstance().create(oracleNul);
		MediaService.getInstance().create(javaNul);
		EmpruntService empruntService=EmpruntService.getInstance();
		empruntService.emprunter(emprunt1);
		empruntService.emprunter(emprunt2);
		empruntService.emprunter(emprunt3);
		//EmpruntService.getInstance().create(emprunt2);
		MediaDao mediaDao1=MediaDao.getInstance();
		//sqlNul.setAuteur("TEST");
		//MediaService.getInstance().update(sqlNul);
		//System.out.println(mediaDao1.findMediaByTitre("SQL").size());
		for (Media media : mediaDao1.findMediaByTitre("POUR")) {
			if(media.getEmpruntEnCours()==null){
				System.out.println(media.getTitre()+" "+media.getAuteur()+" "+media.getType().name());
			}else{
				System.out.println(media.getTitre()+" "+media.getAuteur()+" "+
						media.getEmpruntEnCours().getAdherent().getNom()+" "+
									media.getEmpruntEnCours().getDateRetourPrevue());
			}
				
			
		}
		
		for (Media media : mediaDao1.findMediaByTypeMedia("LIVRE")) {
			if(media.getEmpruntEnCours()==null){
				System.out.println(media.getTitre()+" "+media.getAuteur()+" "+media.getType().name());
			}else{
				System.out.println(media.getTitre()+" "+media.getAuteur()+" "+
						media.getEmpruntEnCours().getAdherent().getNom()+" "+
									media.getEmpruntEnCours().getDateRetourPrevue());
			}
				
			
		}
		
		
		mediaDao1.sortMediaBy(5).forEach((Media media)->{
			if(media.getEmpruntEnCours()==null) {
				System.out.println("SORT $$$$ "+media.getTitre()
						+ " "+media.getAuteur()
						+ " "+media.getType());
			}else{
				System.out.println("SORT $$$$ "+media.getTitre()
						+ " "+media.getAuteur()
						+ " "+media.getType()
								+ " "+media.getEmpruntEnCours().getAdherent().getNom()
								+ " "+media.getEmpruntEnCours().getAdherent().getPrenom()
								+ " "+media.getEmpruntEnCours().getDateRetourPrevue());
			}
		});
				
		System.out.println("by nom ************************************************************************");
		List <Adherent> ad1 = new AdherentDao().findAdherentByNom("BE");
		for (Adherent a : ad1) {
			System.out.println(a.getNom()+" "+a.getPrenom()+" "+a.getDateNaissaince()+" date de paiment"+a.getCotisation().getDatePaiment());
		}
			
		System.out.println("end ***************************************************************************");
		
		
		//empruntDao.create(new Emprunt(new Media(titre, auteur, media), adherent, dateEmprunt, dateRetourEffectif, dateRetourPrevue));
	*/}
		
	
		
		
}
