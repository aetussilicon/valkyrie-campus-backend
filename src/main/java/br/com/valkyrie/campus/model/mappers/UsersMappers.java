package br.com.valkyrie.campus.model.mappers;

import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
import br.com.valkyrie.campus.model.dtos.responses.UsersResponseDto;
import br.com.valkyrie.campus.model.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UsersMappers {

    UsersMappers INSTACE = Mappers.getMapper(UsersMappers.class);

    @Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    Users signupDtoToModel(UsersSignupDto dto);

    UsersResponseDto modelToResponseDto(Users user);
}
