package com.dta.mediatic.media.service;

import java.util.Comparator;

import com.dta.mediatic.media.model.Media;

public class DynamicMediaComparator implements Comparator<Media> {
	private static int compare;
	private static DynamicMediaComparator dynamicMediaComparator;
	
	public static DynamicMediaComparator getInstance(){
		if(dynamicMediaComparator==null){
			dynamicMediaComparator=new DynamicMediaComparator();
			compare=1;
		}
		return dynamicMediaComparator;
	}
	public static DynamicMediaComparator getInstance(int comparBy){
		if(dynamicMediaComparator==null){
			dynamicMediaComparator=new DynamicMediaComparator();
			compare=comparBy;
		}
		return dynamicMediaComparator;
	}
	

	@Override
	public int compare(Media o1, Media o2) {
		if(compare==1)	return o1.compareTo(o2);
		if(compare==-1) return o2.compareTo(o1);
		if(compare==2) return o1.getAuteur().compareToIgnoreCase(o2.getAuteur());
		if(compare==-2) return o2.getAuteur().compareToIgnoreCase(o1.getAuteur());
		if(compare==3) return o1.getType().name().compareToIgnoreCase(o2.getType().name());
		if(compare==-3) return o2.getType().name().compareToIgnoreCase(o1.getType().name());
		if(compare==5){
			if(o1.getEmpruntEnCours()==null) return 1;
			else if(o2.getEmpruntEnCours()==null) return -1;
			else return o1.getEmpruntEnCours().getAdherent().compareTo(o2.getEmpruntEnCours().getAdherent());
		}
		if(compare==-5) {
			if(o1.getEmpruntEnCours()==null) return -1;
			else if(o2.getEmpruntEnCours()==null) return 1;
			else return o2.getEmpruntEnCours().getAdherent().compareTo(o1.getEmpruntEnCours().getAdherent());
		}
		if(compare==6) {
			if(o1.getEmpruntEnCours()==null) return -1;
			else if(o2.getEmpruntEnCours()==null) return 1;
			else return o1.getEmpruntEnCours().getDateRetourPrevue().compareTo(o2.getEmpruntEnCours().getDateRetourPrevue());
		}
		if(compare==-6) {
			if(o1.getEmpruntEnCours()==null) return 1;
			else if(o2.getEmpruntEnCours()==null) return -1;
			else return o2.getEmpruntEnCours().getDateRetourPrevue().compareTo(o1.getEmpruntEnCours().getDateRetourPrevue());
		}
		return 0;
	}

}
