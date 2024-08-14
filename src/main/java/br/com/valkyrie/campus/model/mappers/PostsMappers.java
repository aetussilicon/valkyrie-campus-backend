package br.com.valkyrie.campus.model.mappers;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.entities.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostsMappers {
    PostsMappers INSTANCE = Mappers.getMapper(PostsMappers.class);

    Posts newPostDtoToModel(NewPostDto dto);
}
