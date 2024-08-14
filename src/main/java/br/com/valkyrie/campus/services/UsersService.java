package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.Exceptions.UserAlreadyExistsException;
import br.com.valkyrie.campus.Exceptions.UserNotFoundException;
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

    /** Método para criar um usuário
     *
     * @param dto - Dados do usuário em formato JSON que vem da requisição.
     * @return Retorna o usuário criado.
     * @throws UserAlreadyExistsException - Caso o usuário já esteja cadastrado.
     * */
    public Users createUser (UsersSignupDto dto) {
        Optional<Users> checkUserInDatabase = repo.findUserByEmail(dto.getEmail());
        if (checkUserInDatabase.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        //TODO - Alterar return para um Resposta Completa com status e mensagem.
        return repo.save(mappers.signupDtoToModel(dto));
    }

    /** Método para buscar um usuário
     *
     * @param usertag - Usertag do usuário que se deseja buscar.
     * @return Retorna o usuário encontrado.
     * @throws UserNotFoundException - Caso o usuário não seja encontrado.
     * */
    public Users getUser(String usertag) {
        return findUserByUsertag(usertag);
    }

    private Users findUserByUsertag(String usertag) {
        return repo.findUserByUsertag(usertag).orElseThrow(UserNotFoundException::new);
    }
}
