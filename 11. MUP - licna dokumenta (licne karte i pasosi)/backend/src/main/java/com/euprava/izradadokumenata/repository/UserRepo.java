package com.euprava.izradadokumenata.repository;

import com.euprava.izradadokumenata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
