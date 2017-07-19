package mediatic.dao;

import javax.persistence.EntityManager;

public class DAO<T>{
	
    private Class<T> klass;

    public DAO(Class<T> klass) {
        this.klass = klass;
    }

    public T find(Long id) {
    	
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        try {
        	 T t = entityManager.find(klass, id);
             entityManager.close();
             return t;
        }catch (Exception e) {
        	 DatabaseHelper.rollbackTxAndClose(entityManager);
             throw new RuntimeException(e);
        }
       
    }

    public T create(T t) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        try {
            DatabaseHelper.beginTx(entityManager);
            entityManager.persist(t);
            DatabaseHelper.commitTxAndClose(entityManager);
            return t;
        } catch (Exception e) {
            DatabaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }

    public T update(T t) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        try {
            DatabaseHelper.beginTx(entityManager);
            entityManager.merge(t);
            DatabaseHelper.commitTxAndClose(entityManager);
            return t;
        } catch (Exception e) {
            DatabaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        try {
            DatabaseHelper.beginTx(entityManager);
            entityManager.remove(entityManager.find(klass, id));
            DatabaseHelper.commitTxAndClose(entityManager);
        } catch (Exception e) {
            DatabaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }
}
