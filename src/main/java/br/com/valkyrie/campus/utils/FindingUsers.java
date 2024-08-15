package br.com.valkyrie.campus.utils;

import br.com.valkyrie.campus.exceptions.UserNotFoundException;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Essa classe é responsável pela procura e
 * retorno das entidades de usuários no banco de dados.
 */
@Component
@RequiredArgsConstructor
public class FindingUsers {
    private final UsersRepository repo;

    public Users searchUserById(UUID userId) {
        return repo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public Users findUserbyUsertag(String usertag) {
        return repo.findUserByUsertag(usertag).orElseThrow(UserNotFoundException::new);
    }

}
