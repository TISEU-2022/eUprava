package com.projekat.predskolsko.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "konkurs")
public class Konkurs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "konkurs_id", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vrtic_id", referencedColumnName = "vrtic_id")
	private Vrtic vrtic;
	
	@Column(name = "numberOfChildren", unique = false, nullable = false)
	private Integer numberOfChildren;
	
	@Column(name = "date", unique = false, nullable = false)
	private LocalDate date;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private boolean deleted;

	public Konkurs() {
		super();
	}

	

	public Konkurs(Integer id, Vrtic vrtic, Integer numberOfChildren, LocalDate date, boolean deleted) {
		super();
		this.id = id;
		this.vrtic = vrtic;
		this.numberOfChildren = numberOfChildren;
		this.date = date;
		this.deleted = deleted;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Vrtic getVrtic() {
		return vrtic;
	}

	public void setVrtic(Vrtic vrtic) {
		this.vrtic = vrtic;
	}

	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(Integer numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Konkurs [konkurs_id=" + id  + ", vrtic=" + vrtic + ", numberOfChildren=" + numberOfChildren
				+ ", date=" + date + ", deleted=" + deleted + "]";
	}
	
}
