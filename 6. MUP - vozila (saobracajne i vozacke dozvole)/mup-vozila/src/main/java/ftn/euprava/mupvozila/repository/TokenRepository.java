package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
}
