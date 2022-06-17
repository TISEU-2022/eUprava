package com.example.SluzbaZaposljavanja.model.dto;

import java.time.LocalDate;

public class OglasZaPosaoDto {

    private Integer id;

    private String naziv;

    private String opis;

    private LocalDate datumOd;

    private LocalDate datumDo;

    private Integer firma;

    private Integer vrstaPosla;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}

	public LocalDate getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(LocalDate datumDo) {
		this.datumDo = datumDo;
	}

	public Integer getFirma() {
		return firma;
	}

	public void setFirma(Integer firma) {
		this.firma = firma;
	}

	public Integer getVrstaPosla() {
		return vrstaPosla;
	}

	public void setVrstaPosla(Integer vrstaPosla) {
		this.vrstaPosla = vrstaPosla;
	}
    
    

}
