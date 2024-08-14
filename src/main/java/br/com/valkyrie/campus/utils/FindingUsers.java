package br.com.valkyrie.campus.utils;

import br.com.valkyrie.campus.Exceptions.UserAlreadyExistsException;
import br.com.valkyrie.campus.Exceptions.UserNotFoundException;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindingUsers {
    private final UsersRepository repo;

    public Users findUserbyUsertag(String usertag) {
        return repo.findUserByUsertag(usertag).orElseThrow(UserNotFoundException::new);
    }

}
