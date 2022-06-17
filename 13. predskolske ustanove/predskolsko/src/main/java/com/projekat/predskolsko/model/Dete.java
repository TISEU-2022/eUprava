package com.projekat.predskolsko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "enrolled", unique = false, nullable = false)
	private boolean enrolled;
	
	@ManyToOne
	@JoinColumn(name = "vrtic_id", referencedColumnName = "vrtic_id")
	private Vrtic vrtic;

	public Dete() {
		super();
	}

	public Dete(Integer id, String jmbg, String name, String lastname, String address, String placeOfBirth,
			boolean enrolled, Vrtic vrtic) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.placeOfBirth = placeOfBirth;
		this.enrolled = enrolled;
		this.vrtic = vrtic;
	}

	public Dete(String jmbg, String name, String lastname, String address, String placeOfBirth, boolean enrolled,
			Vrtic vrtic) {
		super();
		this.jmbg = jmbg;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.placeOfBirth = placeOfBirth;
		this.enrolled = enrolled;
		this.vrtic = vrtic;
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

	public boolean isEnrolled() {
		return enrolled;
	}

	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	public Vrtic getVrtic() {
		return vrtic;
	}

	public void setVrtic(Vrtic vrtic) {
		this.vrtic = vrtic;
	}

	@Override
	public String toString() {
		return "Dete [id=" + id + ", jmbg=" + jmbg + ", name=" + name + ", lastname=" + lastname + ", address="
				+ address + ", placeOfBirth=" + placeOfBirth + ", enrolled=" + enrolled + ", vrtic=" + vrtic + "]";
	}

	
}
