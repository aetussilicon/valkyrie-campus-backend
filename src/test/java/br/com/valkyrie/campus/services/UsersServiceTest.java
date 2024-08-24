package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.exceptions.UserAlreadyExistsException;
import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
import br.com.valkyrie.campus.model.dtos.responses.UsersResponseDto;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.enums.UsersRole;
import br.com.valkyrie.campus.model.mappers.UsersMappers;
import br.com.valkyrie.campus.repositories.UsersRepository;
import br.com.valkyrie.campus.utils.FindingUsers;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    @Mock
    private UsersRepository repo;

    @Mock
    private UsersMappers mapper;

    @Mock
    private FindingUsers findingUsers;

    @InjectMocks
    private UsersService service;

    private Users createdUser;
    private UsersResponseDto returnedDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        createdUser = new Users();
        createdUser.setUserId(UUID.randomUUID());
        createdUser.setFullName("Test User");
        createdUser.setUsertag("testuser");
        createdUser.setEmail("test@test.com");
        createdUser.setPassword("123");
        createdUser.setRole(UsersRole.USER);
        createdUser.setCreatedDate(new Date());
        createdUser.setLastUpdatedDate(new Date());

        returnedDto = new UsersResponseDto();
        returnedDto.setFullName("Test User");
        returnedDto.setUsertag("testuser");
        returnedDto.setRole(UsersRole.USER);
        returnedDto.setCreatedDate(new Date());
        returnedDto.setLastUpdatedDate(new Date());
    }

    @Test
    void createUser_validDto_shouldReturnUsersResponseDto() {
        UsersSignupDto signupDto = new UsersSignupDto();
        signupDto.setFullName("Test User");
        signupDto.setUsertag("testuser");
        signupDto.setEmail("test@test.com");
        signupDto.setPassword("123");

        when(findingUsers.searchUserByEmail(signupDto.getEmail())).thenReturn(null);
        when(mapper.signupDtoToModel(signupDto)).thenReturn(createdUser);
        when(repo.save(createdUser)).thenReturn(createdUser);
        when(mapper.modelToResponseDto(createdUser)).thenReturn(returnedDto);

        UsersResponseDto serviceTestDto = service.createUser(signupDto);

        assertEquals(serviceTestDto.getFullName(), returnedDto.getFullName());
        assertEquals(serviceTestDto.getUsertag(), returnedDto.getUsertag());
        assertEquals(serviceTestDto.getRole(), returnedDto.getRole());
        assertEquals(serviceTestDto.getCreatedDate(), returnedDto.getCreatedDate());
        assertEquals(serviceTestDto.getLastUpdatedDate(), returnedDto.getLastUpdatedDate());

        verify(findingUsers, times(1)).searchUserByEmail(signupDto.getEmail());
        verify(mapper, times(1)).signupDtoToModel(signupDto);
        verify(repo, times(1)).save(createdUser);
        verify(mapper, times(1)).modelToResponseDto(createdUser);

    }

    @Test
    void createUser_userExists_shouldThrowUserAlreadyExistsException() {
        UsersSignupDto signupDto = new UsersSignupDto();
        signupDto.setFullName("Test User");
        signupDto.setUsertag("testuser");
        signupDto.setEmail("test@test.com");
        signupDto.setPassword("123");

        doThrow(new UserAlreadyExistsException())
                .when(findingUsers).searchUserByEmail(signupDto.getEmail());

        assertThrows(UserAlreadyExistsException.class, () -> service.createUser(signupDto));
    }


    @Test
    void getUser_userInDatabase() {
        Users user = new Users();
        user.setFullName("Teste User");
        user.setUsertag("testeuser");

        UsersResponseDto foundUser = new UsersResponseDto();
        foundUser.setFullName("Teste User");
        foundUser.setUsertag("testeuser");

        when(findingUsers.findUserbyUsertag(user.getUsertag())).thenReturn(user);
        when(mapper.modelToResponseDto(user)).thenReturn(foundUser);

        UsersResponseDto userResponseDto = service.getUser(user.getUsertag());

        assertNotNull(userResponseDto);
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getUsertag(), foundUser.getUsertag());

        Mockito.verify(findingUsers).findUserbyUsertag(user.getUsertag());
    }
}