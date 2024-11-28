package br.com.facol.uservault.repository;

import br.com.facol.uservault.domain.UserCreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCreateRepository extends JpaRepository<UserCreate, Long> {
    Optional<UserCreate> findByEmail(String username);
}
