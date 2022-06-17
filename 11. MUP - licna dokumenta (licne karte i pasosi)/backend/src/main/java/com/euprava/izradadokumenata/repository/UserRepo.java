package com.euprava.izradadokumenata.repository;

import com.euprava.izradadokumenata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User getUserByUsername(String username);
}
