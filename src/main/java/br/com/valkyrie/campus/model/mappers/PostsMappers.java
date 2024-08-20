package br.com.valkyrie.campus.model.mappers;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.PostsDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.utils.IdGenerator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface PostsMappers {
    PostsMappers INSTANCE = Mappers.getMapper(PostsMappers.class);

    @Mapping(target = "postId", expression = "java(UUID.randomUUID())")
    Posts newPostDtoToModel(NewPostDto dto);

    PostsDto postModelToDto(Posts posts);

    List<PostsDto> modelToPostDto(List<Posts> post);
}
