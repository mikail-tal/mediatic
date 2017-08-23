package com.dta.mediatic.media.model;

public enum TypeMedia {
  LIVRE("LIVRE"),DVD("DVD"),CD("CD");
  private String name;
  private TypeMedia(String s) {
	  this.setName(s);
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
  
}
