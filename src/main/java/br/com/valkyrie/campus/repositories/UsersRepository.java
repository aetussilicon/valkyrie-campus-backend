package br.com.valkyrie.campus.repositories;

import br.com.valkyrie.campus.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findUserByEmail(String email);
}
