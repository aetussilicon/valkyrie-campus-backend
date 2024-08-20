package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.PostsDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.PostsMappers;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository repo;
    private final PostsMappers mapper;
    private final FindingUsers findingUsers;
    private final FindingPosts findingPosts;

    public Posts publishNewPost(NewPostDto dto) {
        Users postedBy = findingUsers.findUserbyUsertag(dto.getUsertag());
        dto.setPostedBy(postedBy);

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        return repo.save(mapper.newPostDtoToModel(dto));
    }

    public PostsDto listPost(UUID postId) {
        PostsDto post = mapper.postModelToDto(findingPosts.searchPostById(postId));
        post.setFormatedDate(formatDate(post.getCreatedDate()));

        return post;
    }

    public List<PostsDto> listPosts() {
        List<PostsDto> posts = mapper.modelToPostDto(repo.findAll());
        for (PostsDto post : posts) {
            post.setFormatedDate(formatDate(post.getCreatedDate()));
        }
        return posts;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static String formatDate(Date date) {
        return dateFormat.format(date);
    }

}
