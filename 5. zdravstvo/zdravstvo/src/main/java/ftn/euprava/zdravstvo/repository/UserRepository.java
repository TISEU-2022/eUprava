package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
