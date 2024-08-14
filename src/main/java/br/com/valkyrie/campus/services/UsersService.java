package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.UsersMappers;
import br.com.valkyrie.campus.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repo;
    private final UsersMappers mappers;

    public Users createUser (UsersSignupDto dto) {
        Optional<Users> checkUserInDatabase = repo.findUserByEmail(dto.getEmail());
        if (checkUserInDatabase.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        return repo.save(mappers.signupDtoToModel(dto));
    }
}
