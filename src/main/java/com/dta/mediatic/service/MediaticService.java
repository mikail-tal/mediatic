package com.dta.mediatic.service;

public interface MediaticService <T>{
	
	public long count() ;

	
	public void delete(Long arg0) ;

	
	public void delete(T arg0) ;

	
	public void delete(Iterable<? extends T> arg0) ;

	
	public void deleteAll() ;

	
	public boolean exists(Long arg0) ;
	
	public Iterable<T> findAll() ;

	
	public Iterable<T> findAll(Iterable<Long> arg0);

	
	public T findOne(Long arg0) ;
	
	public <S extends T> S save(S arg0) ;
	
	public <S extends T> Iterable<S> save(Iterable<S> arg0) ;

}
