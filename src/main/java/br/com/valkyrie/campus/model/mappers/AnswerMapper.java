package br.com.valkyrie.campus.model.mappers;

import br.com.valkyrie.campus.model.dtos.answer.NewAnswerDto;
import br.com.valkyrie.campus.model.dtos.responses.AnswerResponseDto;
import br.com.valkyrie.campus.model.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    // AnswerDto to Answer
    @Mapping(target = "accepted", ignore = true)
    @Mapping(target = "answerId", expression = "java(UUID.randomUUID())")
    Answer answerDtoToModel(NewAnswerDto dto);

    // Answer to AnswerDto
    AnswerResponseDto answerToDto(Answer answer);
}
