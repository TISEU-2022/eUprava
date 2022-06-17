package com.projekat.predskolsko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sluzbenik")
public class Sluzbenik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sluzbenik_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "jmbg", unique = true, nullable = false)
	private String jmbg;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "lastname", unique = false, nullable = false)
	private String lastname;

	@Column(name = "address", unique = false, nullable = false)
	private String address;

	@Column(name = "deleted", unique = false, nullable = false)
	private boolean deleted;
	
	

	public Sluzbenik() {
		super();
	}

	public Sluzbenik(Integer id, String jmbg, String name, String lastname, String address, boolean deleted) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
