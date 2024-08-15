package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.exceptions.UserAlreadyExistsException;
import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.enums.UsersRole;
import br.com.valkyrie.campus.model.mappers.UsersMappers;
import br.com.valkyrie.campus.repositories.UsersRepository;
import br.com.valkyrie.campus.utils.FindingUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsersServiceTest {

    @Mock
    private UsersRepository repo;

    @Mock
    private UsersMappers mapper;

    @Mock
    private FindingUsers findingUsers;

    @InjectMocks
    private UsersService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_userNotInDatabase() {
        // Cria um objeto de UsersSignupDto com dados fictícios.
        UsersSignupDto dto = new UsersSignupDto();
        dto.setFullName("Teste User");
        dto.setUsertag("testeuser");
        dto.setEmail("teste@teste.com");
        dto.setPassword("123");
        dto.setRole(UsersRole.USER);

        // Cria um objeto de Users com dados fictícios.
        Users user = new Users();
        user.setFullName("Teste User");
        user.setUsertag("testeuser");
        user.setEmail("teste@teste.com");
        user.setPassword("123");
        user.setRole(UsersRole.USER);

        // Configura o comportamento do repositório para retornar um Optional vazio quando findUserByEmail for chamado com o email do dto.
        when(repo.findUserByEmail(dto.getEmail())).thenReturn(Optional.empty());
        //Configura o comportamento do mapper para converter o dto em um objeto de Users.
        when(mapper.signupDtoToModel(dto)).thenReturn(user);
        // Configura o comportamento do repositório para salvar o usuário e retornar o mesmo.
        when(repo.save(user)).thenReturn(user);

        // Chama o método createUser do service.
        Users createdUser = service.createUser(dto);

        // Verifica se o usuário retornado é igual ao usuário criado.
        assertNotNull(createdUser);
        assertEquals(user.getFullName(), createdUser.getFullName());
        assertEquals(user.getUsertag(), createdUser.getUsertag());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(user.getRole(), createdUser.getRole());

        // Verifica se os métodos do repositório e do mapper foram chamados corretamente.
        Mockito.verify(repo).findUserByEmail(dto.getEmail());
        Mockito.verify(mapper).signupDtoToModel(dto);
        Mockito.verify(repo).save(user);
    }

    @Test
    void createUser_userInDatabase() {
        UsersSignupDto dto = new UsersSignupDto();
        dto.setFullName("Teste User");
        dto.setUsertag("testeuser");
        dto.setEmail("teste@teste.com");
        dto.setPassword("123");
        dto.setRole(UsersRole.USER);

        Users user = new Users();
        user.setFullName("Teste User");
        user.setUsertag("testeuser");
        user.setEmail("teste@teste.com");

        // Configura o comportamento do repositório para retornar um Optional com o usuário quando findUserByEmail for chamado com o email do dto.
        when(repo.findUserByEmail(dto.getEmail())).thenReturn(Optional.of(user));

        // Verifica se o método createUser lança uma exceção quando o usuário já está cadastrado.
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> service.createUser(dto));
        assertEquals("User already exists", exception.getMessage());

        // Verifica se o método findUserByEmail do repositório foi chamado.
        Mockito.verify(repo).findUserByEmail(dto.getEmail());
    }

    @Test
    void getUser_userInDatabase() {
        Users user = new Users();
        user.setFullName("Teste User");
        user.setUsertag("testeuser");
        user.setEmail("teste@teste.com");

        when(findingUsers.findUserbyUsertag(user.getUsertag())).thenReturn(user);

        Users foundUser = service.getUser(user.getUsertag());

        assertNotNull(foundUser);
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getUsertag(), foundUser.getUsertag());
        assertEquals(user.getEmail(), foundUser.getEmail());

        Mockito.verify(findingUsers).findUserbyUsertag(user.getUsertag());
    }
}