package com.eUprava.eUprava.service;

public interface UverenjaService {

    public boolean hasLekarskoUverenje(String ime,String prezime,String jmbg);
    public boolean hasSudskoUverenje(String ime,String prezime,String jmbg);
    public boolean hasVazecaLicna(String ime,String prezime,String jmbg);

}
