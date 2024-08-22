package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.PostsResponseDto;
import br.com.valkyrie.campus.model.dtos.answer.UpvoteDownvoteDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.enums.UpvoteDownvote;
import br.com.valkyrie.campus.model.mappers.PostsMappers;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostsServiceTest {

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private PostsMappers postsMappers;

    @Mock
    private FindingUsers findingUsers;

    @Mock
    private FindingPosts findingPosts;

    @InjectMocks
    private PostsService postsService;

    private NewPostDto newPostDto;
    private UpvoteDownvoteDto upvoteDownvoteDto;
    private Posts post;
    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        newPostDto = new NewPostDto();
        newPostDto.setUsertag("usertag");
        newPostDto.setContent("Post content test");

        post = new Posts();
        post.setPostId(UUID.randomUUID());

        user = new Users();
        user.setUserId(UUID.randomUUID());

        upvoteDownvoteDto = new UpvoteDownvoteDto();
    }

    @Test
    void publishNewPost() {
    }

    @Test
    void updateUpvoteDownvote_Upvote() {
        upvoteDownvoteDto.setPostId(UUID.randomUUID());
        upvoteDownvoteDto.setVote(UpvoteDownvote.UPVOTE);

        when(findingPosts.searchPostById(any(UUID.class))).thenReturn(post);
        when(postsMappers.updateUpvoteDownvote(any(UpvoteDownvoteDto.class))).thenReturn(post);

        postsService.updateUpvoteDownvote(upvoteDownvoteDto);

        assertEquals(1, post.getUpvote());
        verify(postsRepository, times(1)).save(any(Posts.class));
    }

    @Test
    void listPost_success() {

        // Criando e configurando o PostsDto(Objeto de retorno)
        PostsResponseDto postsDto = new PostsResponseDto();
        postsDto.setTitle("Test Post");
        postsDto.setContent("Test Content");
        postsDto.setCreatedDate(new Date());

        // Configurando o post
        post.setTitle("Test Post");
        post.setContent("Test Content");
        post.setCreatedDate(new Date());

        // Configurando comportamento dos mocks
        when(findingPosts.searchPostById(any(UUID.class))).thenReturn(post);
        when(postsMappers.postModelToDto(any(Posts.class))).thenReturn(postsDto);

        PostsResponseDto getPost = postsService.listPost(post.getPostId());
        assertEquals("Test Post", getPost.getTitle());
        assertEquals("Test Content", getPost.getContent());

        verify(findingPosts, times(1)).searchPostById(post.getPostId());
    }
}