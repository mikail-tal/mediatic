package mediatic;

import java.time.LocalDate;

import adherent.dao.AdherentDao;
import adherent.model.Adherent;
import cotisation.dao.CotisationDao;
import cotisation.model.Cotisation;
import emprunt.dao.EmpruntDao;
import emprunt.model.Emprunt;
import media.dao.MediaDao;
import media.model.Media;
import media.model.TypeMedia;

public class Main {
	
	public static void main(String[] args) {
		
		
		EmpruntDao empruntDao=new EmpruntDao();
		CotisationDao cotisationDao=new CotisationDao();
		AdherentDao adherentDao=new AdherentDao();
		MediaDao mediaDao=new MediaDao();
		Cotisation cotisation=new Cotisation(500l,LocalDate.of(2017, 7, 10));
		Adherent adherent=new Adherent("BENGHAL", "AYOUB", LocalDate.of(1989, 8, 14), "ayoub.benghal@gmail.com", "18 RUE MIRON", "06000", "NICE");
		adherent.setCotisation(cotisation);
		
		
		Media javaNul=new Media("JAVA POUR LES NULS", "ABDEL ET DIOGO", TypeMedia.LIVRE);
		Media oracleNul=new Media("ORACLE POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.DVD);
		Media sqlNul=new Media("SQL POUR LES NULS", "DIOGO ET ABDEL", TypeMedia.CD);
		
		mediaDao.create(javaNul);
		mediaDao.create(oracleNul);
		mediaDao.create(sqlNul);
		cotisationDao.create(cotisation);
		adherentDao.create(adherent);
		
		Emprunt emprunt1=new Emprunt(sqlNul, adherent, LocalDate.now(), null, LocalDate.now().plusMonths(2));
		Emprunt emprunt2=new Emprunt(oracleNul, adherent, LocalDate.of(2017, 6, 20), LocalDate.now(),LocalDate.of(2017, 6, 20).plusMonths(2));
		empruntDao.create(emprunt1);
		empruntDao.create(emprunt2);
		
		
		
		
		
		
		//empruntDao.create(new Emprunt(new Media(titre, auteur, media), adherent, dateEmprunt, dateRetourEffectif, dateRetourPrevue));
	}
		
	
		
		
}
