package com.euprava.izradadokumenata.service.implementation;

import com.euprava.izradadokumenata.model.User;
import com.euprava.izradadokumenata.model.dto.user.LoggedUserDto;
import com.euprava.izradadokumenata.model.dto.user.UserMapper;
import com.euprava.izradadokumenata.model.dto.user.UserSetupDto;
import com.euprava.izradadokumenata.repository.UserRepo;
import com.euprava.izradadokumenata.service.UserService;
import com.euprava.izradadokumenata.util.exceptions.UserMissingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getUserByUsername(String username) {
        User user = userRepo.getUserByUsername(username);
        if (user == null) throw new UserMissingException();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) throw new UserMissingException();
        return user.get();
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean initialSetup(LoggedUserDto loggedUserDto) {
        if (userRepo.getUserByUsername(loggedUserDto.getUsername()) == null) {
            userRepo.save(User.builder().username(loggedUserDto.getUsername()).initialRequest(true).build());
            return true;
        } else return userRepo.getUserByUsername(loggedUserDto.getUsername()).isInitialRequest();
    }

    @Override
    public void userDataSetup(UserSetupDto userSetupDto) {
        User user = getUserByUsername(userSetupDto.getUsername());
        UserMapper.INSTANCE.updateUserFromLoggedUser(user, userSetupDto);
        user.setInitialRequest(false);
        userRepo.save(user);
    }
}
