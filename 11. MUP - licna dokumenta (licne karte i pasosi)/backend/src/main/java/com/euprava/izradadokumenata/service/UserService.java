package com.euprava.izradadokumenata.service;

import com.euprava.izradadokumenata.model.User;
import com.euprava.izradadokumenata.model.dto.user.LoggedUserDto;
import com.euprava.izradadokumenata.model.dto.user.UserSetupDto;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User getUserById(String id);

    boolean initialSetup(LoggedUserDto loggedUserDto);

    void userDataSetup(UserSetupDto userSetupDto);
}
