package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.Token;
import ftn.euprava.mupvozila.repository.TokenRepository;
import ftn.euprava.mupvozila.service.ITokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService implements ITokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String save(String tokenData) {
        Token token = new Token();
        token.setToken(tokenData);
        return (tokenRepository.save(token)).getToken();
    }

    @Override
    public String findToken() {
        Token tokenObj = tokenRepository.findAll().get(tokenRepository.findAll().size()-1);
        String token = tokenObj.getToken();
        tokenRepository.deleteAll();
        return (token);
    }

    @Override
    public List<Token> findAll(){
        return tokenRepository.findAll();
    }

    @Override
    public void deleteAll(){
        tokenRepository.deleteAll();
    }

}
