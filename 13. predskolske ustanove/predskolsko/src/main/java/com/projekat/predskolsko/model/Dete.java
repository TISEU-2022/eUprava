package com.projekat.predskolsko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dete")
public class Dete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dete_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "jmbg", unique = true, nullable = false)
	private String jmbg;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "lastname", unique = false, nullable = false)
	private String lastname;

	@Column(name = "address", unique = false, nullable = false)
	private String address;

	@Column(name = "placeOfBirth", unique = false, nullable = false)
	private String placeOfBirth;

	@Column(name = "gender", unique = false, nullable = false)
	private boolean gender;

	@Column(name = "deleted", unique = false, nullable = false)
	private boolean deleted;

	public Dete(Integer id, String jmbg, String name, String lastname, String address, String placeOfBirth,
			boolean gender, boolean deleted) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.placeOfBirth = placeOfBirth;
		this.gender = gender;
		this.deleted = deleted;
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

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
