package br.com.valkyrie.campus.model.mappers;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.responses.PostsResponseDto;
import br.com.valkyrie.campus.model.dtos.answer.UpvoteDownvoteDto;
import br.com.valkyrie.campus.model.entities.Posts;
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

    Posts updateUpvoteDownvote(UpvoteDownvoteDto dto);

    @Mapping(target = "postedBy", source = "postedBy.fullName")
    PostsResponseDto postModelToDto(Posts posts);

    List<PostsResponseDto> modelToPostDto(List<Posts> post);
}
