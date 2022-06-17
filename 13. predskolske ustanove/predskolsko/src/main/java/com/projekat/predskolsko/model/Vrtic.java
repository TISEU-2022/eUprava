package com.projekat.predskolsko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vrtic")
public class Vrtic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vrtic_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "address", unique = false, nullable = false)
	private String adress;
	
	@Column(name = "numberOfEmployees", unique = false, nullable = false)
	private Integer numberOfEmployees;
	
	@Column(name = "phoneNumber", unique = false, nullable = false)
	private String phoneNumber;
	
	@Column(name = "numberOfChildren", unique = false, nullable = false)
	private Integer numberOfChildren;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private boolean deleted;
	
	
	public Vrtic() {
		super();
	}


	public Vrtic(Integer id, String name, String adress, Integer numberOfEmployees, String phoneNumber,
			Integer numberOfChildren, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.numberOfEmployees = numberOfEmployees;
		this.phoneNumber = phoneNumber;
		this.numberOfChildren = numberOfChildren;
		this.deleted = deleted;
	}


	public Integer getVrtic_id() {
		return id;
	}


	public void setVrtic_id(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String address) {
		this.adress = address;
	}


	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}


	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}


	public void setNumberOfChildren(Integer numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "Vrtic [id=" + id + ", name=" + name + ", address=" + adress + ", numberOfEmployees="
				+ numberOfEmployees + ", phoneNumber=" + phoneNumber + ", numberOfChildren=" + numberOfChildren
				+ ", deleted=" + deleted + "]";
	}
	
}
