package org.advancelab.citizensdata.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private int ward;
	private String metropolis;
	private String district;
	public int getWard() {
		return ward;
	}
	public void setWard(int ward) {
		this.ward = ward;
	}
	public String getMetropolis() {
		return metropolis;
	}
	public void setMetropolis(String metropolis) {
		this.metropolis = metropolis;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	

}
