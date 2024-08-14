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


    /** Método para criar um usuário
     *
     * @param dto - Dados do usuário em formato JSON que vem da requisição.
     * @return Retorna o usuário criado.
     * @throws RuntimeException - Caso o usuário já esteja cadastrado.
     * */
    public Users createUser (UsersSignupDto dto) {
        Optional<Users> checkUserInDatabase = repo.findUserByEmail(dto.getEmail());
        if (checkUserInDatabase.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado"); //TODO - Criar exceção personalizada
        }

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        //TODO - Alterar return para um Resposta Completa com status e mensagem.
        return repo.save(mappers.signupDtoToModel(dto));
    }
}
