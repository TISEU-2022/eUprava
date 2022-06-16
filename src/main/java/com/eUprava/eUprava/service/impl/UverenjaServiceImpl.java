package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.service.UverenjaService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class UverenjaServiceImpl implements UverenjaService {

    @Override
    public boolean hasLekarskoUverenje(String ime, String prezime, String jmbg) {
        try {
            URL url=new URL("http://localhost:5002/api/medical-certificate");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"name\":"+ ime + ", \"lastname\":"+prezime +", \"jmbg\":"+jmbg+",\"purpose\":\"Lekarsko Uverenje\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (con.getResponseCode()==200 )return true;
            return false;
        } catch (MalformedURLException e) {
            //throw new RuntimeException(e);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean hasSudskoUverenje(String ime, String prezime, String jmbg) {
        try {
            URL url=new URL("http://localhost:5002/api/sudsko-uverenje");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"name\":"+ ime + ", \"lastname\":"+prezime +", \"jmbg\":"+jmbg+",\"purpose\":\"Lekarsko Uverenje\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (con.getResponseCode()==200 )return true;
            return false;
        } catch (MalformedURLException e) {
            //throw new RuntimeException(e);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean hasVazecaLicna(String ime, String prezime, String jmbg) {
        try {
            URL url=new URL("http://localhost:5002/api/vazeca-licna");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"name\":"+ ime + ", \"lastname\":"+prezime +", \"jmbg\":"+jmbg+",\"purpose\":\"Lekarsko Uverenje\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (con.getResponseCode()==200 )return true;
            return false;
        } catch (MalformedURLException e) {
            //throw new RuntimeException(e);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }

        return false;
    }
}
