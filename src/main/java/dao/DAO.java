package dao;

import javax.persistence.EntityManager;

public class DAO<T>{
	private Class<T>klass;
	public DAO(Class<T> klass) {
		this.klass=klass;
	}
	
	 public void create(T type){
		  EntityManager en=DatabaseHelper.createEntityManager();
			DatabaseHelper.beginTx(en);
			en.persist(type);
			DatabaseHelper.commitTxAndClose(en);
	  }
	 public void update(T type){
		  EntityManager en=DatabaseHelper.createEntityManager();
			DatabaseHelper.beginTx(en);
			en.merge(type);
			DatabaseHelper.commitTxAndClose(en);
			
	  }
	  public void delete(T type){
		  
	  }
	 public   T read(int id){
		 EntityManager en=DatabaseHelper.createEntityManager();
			DatabaseHelper.beginTx(en);
			T t=en.find(klass, id);
			DatabaseHelper.commitTxAndClose(en);
			return t;
		   
	   }

		



		  
	   

}
