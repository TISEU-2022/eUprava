package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.Token;

import java.util.List;

public interface ITokenService {
    String save(String token);

    String findToken();

    List<Token> findAll();

    void deleteAll();
}
