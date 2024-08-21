package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.PostsDto;
import br.com.valkyrie.campus.model.dtos.answer.UpvoteDownvoteDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.PostsMappers;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.utils.DefaultDateFormatter;
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
    // Repositórios e mappers
    private final PostsRepository repo;
    private final PostsMappers mapper;
    private final FindingUsers findingUsers;
    private final FindingPosts findingPosts;
    private final DefaultDateFormatter defaultDateFormatter;

    /**
     * Publicar um novo post
     *
     * @param dto NewPostDto
     * @return Posts
     */
    public Posts publishNewPost(NewPostDto dto) {
        Users postedBy = findingUsers.findUserbyUsertag(dto.getUsertag());
        dto.setPostedBy(postedBy);

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        return repo.save(mapper.newPostDtoToModel(dto));
    }

    /**
     * Atualizar o voto de um post
     *
     * @param dto UpvoteDownvoteDto
     */
    public void updateUpvoteDownvote(UpvoteDownvoteDto dto) {
        Posts post = findingPosts.searchPostById(dto.getPostId());
        switch (dto.getVote()) {
            case UPVOTE:
                post.setUpvote(post.getUpvote() + 1);
                break;
            case DOWNVOTE:
                post.setDownvote(post.getDownvote() + 1);
                break;
        }

        repo.save(post);
    }

    /**
     * Listar um post
     *
     * @param postId UUID
     * @return PostsDto
     */
    public PostsDto listPost(UUID postId) {
        PostsDto post = mapper.postModelToDto(findingPosts.searchPostById(postId));
        post.setFormatedDate(defaultDateFormatter.formatDate(post.getCreatedDate()));

        return post;
    }

    /**
     * Listar todos os posts
     *
     * @return List<PostsDto>
     */
    public List<PostsDto> listPosts() {
        List<PostsDto> posts = mapper.modelToPostDto(repo.findAll());
        for (PostsDto post : posts) {
            post.setFormatedDate(defaultDateFormatter.formatDate(post.getCreatedDate()));
        }
        return posts;
    }
}
