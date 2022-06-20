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
	private Integer vrtic_id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "address", unique = false, nullable = false)
	private String address;
	
	@Column(name = "numberOfEmployees", unique = false, nullable = false)
	private Integer numberOfEmployees;
	
	@Column(name = "phoneNumber", unique = false, nullable = false)
	private String phoneNumber;
	
	@Column(name = "numberOfChildren", unique = false, nullable = false)
	private Integer numberOfChildren;
	

	public Vrtic() {
		super();
	}
	
	
	public Vrtic(Integer vrtic_id, String name, String address, Integer numberOfEmployees, String phoneNumber,
			Integer numberOfChildren) {
		super();
		this.vrtic_id = vrtic_id;
		this.name = name;
		this.address = address;
		this.numberOfEmployees = numberOfEmployees;
		this.phoneNumber = phoneNumber;
		this.numberOfChildren = numberOfChildren;
	}



	public Integer getVrtic_id() {
		return vrtic_id;
	}

	public void setVrtic_id(Integer vrtic_id) {
		this.vrtic_id = vrtic_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Vrtic [vrtic_id=" + vrtic_id + ", name=" + name + ", address=" + address + ", numberOfEmployees="
				+ numberOfEmployees + ", phoneNumber=" + phoneNumber + ", numberOfChildren=" + numberOfChildren + "]";
	}
	
	
}
