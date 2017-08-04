package adherent.service;

import java.util.Comparator;

import adherent.model.Adherent;

public class DynamicAdherentComparator implements Comparator<Adherent> {
	private static int compare;
	private static DynamicAdherentComparator dynamicAdherentComparator;
	
	public static DynamicAdherentComparator getInstance(){
		if(dynamicAdherentComparator==null){
			dynamicAdherentComparator=new DynamicAdherentComparator();
			compare=2;
		}
		return dynamicAdherentComparator;
	}
	public static DynamicAdherentComparator getInstance(int comparBy){
		if(dynamicAdherentComparator==null){
			dynamicAdherentComparator=new DynamicAdherentComparator();
			compare=comparBy;
		}
		return dynamicAdherentComparator;
	}
	

	@Override
	public int compare(Adherent o1, Adherent o2) {
		if(compare==1)	return o1.getId().compareTo(o2.getId());
		if(compare==-1) return o2.getId().compareTo(o1.getId());
		if(compare==2) return o1.compareTo(o2);
		if(compare==-2) return o2.compareTo(o1);
		if(compare==3) return o1.getDateNaissaince().compareTo(o2.getDateNaissaince());
		if(compare==-3) return o2.getDateNaissaince().compareTo(o1.getDateNaissaince());
		if(compare==5) 
			if(o1.abonnementIsValide() && o2.abonnementIsValide()) return o1.compareTo(o2);
			else if(o1.abonnementIsValide()) return -1;
			else if(o2.abonnementIsValide()) return 1;
			else return o1.compareTo(o2);
		
			
		
		if(compare==-5) 
			if(o1.abonnementIsValide() && o2.abonnementIsValide()) return o2.compareTo(o1);
			else if(o1.abonnementIsValide()) return 1;
			else if(o2.abonnementIsValide()) return -1;
			else return o2.compareTo(o1);
		if(compare==6) {
			if(o1.getEmprunt().isEmpty() && o2.getEmprunt().isEmpty()) return o1.compareTo(o2);
			else if(o1.getEmprunt().isEmpty()) return 1;
			else if(o2.getEmprunt().isEmpty()) return -1;
			else return ((Integer)(o1.getEmprunt().size())).compareTo((Integer)(o2.getEmprunt().size()));
		}
		if(compare==-6) {
			if(o1.getEmprunt().isEmpty() && o2.getEmprunt().isEmpty()) return o2.compareTo(o1);
			else if(o1.getEmprunt().isEmpty()) return -1;
			else if(o2.getEmprunt().isEmpty()) return 1;
			else return ((Integer)(o2.getEmprunt().size())).compareTo((Integer)(o1.getEmprunt().size()));
		}
		return 0;
	}

}
