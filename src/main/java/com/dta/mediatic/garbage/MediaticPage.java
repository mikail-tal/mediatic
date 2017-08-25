package com.dta.mediatic.garbage;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public  class MediaticPage<T> implements Page<T>{
	
	
		private Page<T> page;
		private Comparator<T>comparator;
		
		
		

	@Override
	public int getNumber() {
		return page.getNumber();
	}

	@Override
	public int getSize() {
		return page.getSize();
	}

	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	@Override
	public List<T> getContent() {
		List<T> list=page.getContent();
		list.sort(comparator);
		return list;
	}

	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	@Override
	public Sort getSort() {
		return page.getSort();
	}

	@Override
	public boolean isFirst() {
		return page.isFirst();
	}

	@Override
	public boolean isLast() {
		return page.isLast();
	}

	@Override
	public boolean hasNext() {
		return page.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	@Override
	public Pageable nextPageable() {
		return page.nextPageable();
	}

	@Override
	public Pageable previousPageable() {
		return page.previousPageable();
	}

	@Override
	public Iterator<T> iterator() {
		return page.iterator();
	}

	@Override
	public int getTotalPages() {
		return page.getTotalPages();
	}

	@Override
	public long getTotalElements() {
		return page.getTotalElements();
	}

	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		return page.map(converter);
	}

}
