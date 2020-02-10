package org.advancelab.citizensdata.model;

import javax.persistence.Embeddable;

@Embeddable
public class Parents {
	
	private String mother;
	private String father;
	
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	
	

}
