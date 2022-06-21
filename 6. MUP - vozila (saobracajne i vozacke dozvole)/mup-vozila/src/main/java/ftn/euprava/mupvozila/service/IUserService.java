package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.User;

import java.util.List;

public interface IUserService {

    User findOne(Long id);

    List<User> findAll(User user);

    User save(User user);

    void delete(Long id);
}
