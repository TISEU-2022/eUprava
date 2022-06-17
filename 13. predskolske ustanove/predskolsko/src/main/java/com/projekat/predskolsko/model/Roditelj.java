package com.projekat.predskolsko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roditelj")
public class Roditelj {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roditelj_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "lastname", unique = false, nullable = false)
	private String lastname;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "address", unique = false, nullable = false)
	private String address;

	@Column(name = "jmbg", unique = true, nullable = false)
	private String jmbg;
	
	@Column(name = "placeOfBirth", unique = false, nullable = false)
	private String placeOfBirth;
	
	@Column(name = "gender", unique = false, nullable = false)
	private boolean gender;

	@Column(name = "blocked", unique = false, nullable = false)
	private boolean blocked;
	
	

	public Roditelj() {
		super();
	}

	public Roditelj(Integer id, String name, String lastname, String username, String password, String address,
			String jmbg, String placeOfBirth, boolean gender, boolean blocked) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.address = address;
		this.jmbg = jmbg;
		this.placeOfBirth = placeOfBirth;
		this.gender = gender;
		this.blocked = blocked;
	}



	public Integer getRoditelj_id() {
		return id;
	}

	public void setRoditelj_id(Integer id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
}
