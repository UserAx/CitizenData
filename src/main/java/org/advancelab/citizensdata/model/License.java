package org.advancelab.citizensdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class License {
	//Remove @Id if license is to be updated since once set, if no license id is provided,
	//i.e. null while setting, we can not add in the future.
	@Id
	private String licenseId;
	private String blood;
	private String catagory;
	private CustomDate expirydate;
	
	
	public License() {
		// TODO Auto-generated constructor stub
	}
	
	public String getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public CustomDate getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(CustomDate expirydate) {
		this.expirydate = expirydate;
	}
}
