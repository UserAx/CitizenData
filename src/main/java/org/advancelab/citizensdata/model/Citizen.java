package org.advancelab.citizensdata.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.type.TrueFalseType;

@Entity
public class Citizen {
	
	@Id
	private String cardId;
	private String name;
	private Address address;
	private Parents parents;
	@Column(name = "Partner")
	private String spouse;
	private CustomDate dateofbitrth;
	
	@OneToOne(cascade = {javax.persistence.CascadeType.PERSIST,
						javax.persistence.CascadeType.MERGE, 
						javax.persistence.CascadeType.REFRESH}
						,fetch = FetchType.LAZY)
	@JoinColumn(name = "licenseId")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonbTransient
	private License license;
	
	public Citizen() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Parents getParents() {
		return parents;
	}

	public void setParents(Parents parents) {
		this.parents = parents;
	}

	public CustomDate getDateofbitrth() {
		return dateofbitrth;
	}

	public void setDateofbitrth(CustomDate dateofbitrth) {
		this.dateofbitrth = dateofbitrth;
	}
	
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	
	//For links..
	@Transient
	@JsonbProperty
	List<Links> links = new ArrayList<>();
	public List<Links> getLinks() {
		return links;
	}
	public void setLinks(List<Links> links) {
		this.links = links;
	}
}
