package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.exceptions.PostNotFoundException;
import br.com.valkyrie.campus.exceptions.UserNotFoundException;
import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.responses.PostsResponseDto;
import br.com.valkyrie.campus.model.dtos.answer.UpvoteDownvoteDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.enums.UpvoteDownvote;
import br.com.valkyrie.campus.model.mappers.PostsMappers;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostsServiceTest {

    @Mock
    private PostsRepository repo;

    @Mock
    private PostsMappers mapper;

    @Mock
    private FindingUsers findingUsers;

    @Mock
    private FindingPosts findingPosts;

    @InjectMocks
    private PostsService service;

    // Entidades
    private Posts createdPost;
    private Users postAuthor;

    // DTOs
    private NewPostDto newPostDto;
    private PostsResponseDto responseDto;
    private UpvoteDownvoteDto upvoteDownvoteDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        postAuthor = new Users();
        postAuthor.setUserId(UUID.randomUUID());
        postAuthor.setUsertag("testuser");
        postAuthor.setFullName("Test User");

        newPostDto = new NewPostDto();
        newPostDto.setTitle("Test Post Title");
        newPostDto.setContent("Test Post Content");
        newPostDto.setPostedBy(postAuthor);
        newPostDto.setUsertag(postAuthor.getUsertag());

        upvoteDownvoteDto = new UpvoteDownvoteDto();

        createdPost = new Posts();
        createdPost.setTitle(newPostDto.getTitle());
        createdPost.setContent(newPostDto.getContent());
        createdPost.setPostedBy(postAuthor);
        newPostDto.setUsertag(postAuthor.getUsertag());

        responseDto = new PostsResponseDto();
        responseDto.setTitle(createdPost.getTitle());
        responseDto.setContent(createdPost.getContent());
        responseDto.setPostedBy(postAuthor.getFullName());
        responseDto.setUsertag(createdPost.getUsertag());
    }

    @Test
    @DisplayName("Test Publish New Post: Success")
    void publishNewPost_Success() {
        when(findingUsers.findUserbyUsertag(newPostDto.getUsertag())).thenReturn(postAuthor);
        when(mapper.newPostDtoToModel(newPostDto)).thenReturn(createdPost);
        when(repo.save(createdPost)).thenReturn(createdPost);
        when(mapper.postModelToDto(createdPost)).thenReturn(responseDto);

        PostsResponseDto returnedPost = service.publishNewPost(newPostDto);

        assertNotNull(returnedPost);
        assertEquals(responseDto.getTitle(), returnedPost.getTitle());
        assertEquals(responseDto.getContent(), returnedPost.getContent());
        assertEquals(responseDto.getPostedBy(), returnedPost.getPostedBy());
        assertEquals(responseDto.getUsertag(), returnedPost.getUsertag());

        verify(findingUsers).findUserbyUsertag(newPostDto.getUsertag());
        verify(mapper).newPostDtoToModel(newPostDto);
        verify(repo).save(createdPost);
        verify(mapper).postModelToDto(createdPost);

    }

    @Test
    @DisplayName("Publish New Post: Author Not Found")
    void publishNewPost_PostAuthorNotFound() {
        when(findingUsers.findUserbyUsertag(newPostDto.getUsertag())).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> service.publishNewPost(newPostDto));

        verify(findingUsers).findUserbyUsertag(newPostDto.getUsertag());
        verify(mapper, never()).newPostDtoToModel(any(NewPostDto.class));
        verify(repo, never()).save(any(Posts.class));
    }

    @Test
    @DisplayName("Update Upvote")
    void updateUpvoteDownvote_Upvote() {
        upvoteDownvoteDto.setPostId(UUID.randomUUID());
        upvoteDownvoteDto.setVote(UpvoteDownvote.UPVOTE);
        when(findingPosts.searchPostById(upvoteDownvoteDto.getPostId())).thenReturn(createdPost);

        service.updateUpvoteDownvote(upvoteDownvoteDto);

        assertEquals(1, createdPost.getUpvote());

        verify(repo).save(createdPost);
    }

    @Test
    @DisplayName("Update Downvote")
    void updateUpvoteDownvote_Downvote() {
        upvoteDownvoteDto.setPostId(UUID.randomUUID());
        upvoteDownvoteDto.setVote(UpvoteDownvote.DOWNVOTE);

        when(findingPosts.searchPostById(upvoteDownvoteDto.getPostId())).thenReturn(createdPost);

        service.updateUpvoteDownvote(upvoteDownvoteDto);

        assertEquals(1, createdPost.getDownvote());

        verify(repo).save(createdPost);
    }

    @Test
    void listPost_Success() {
        when(findingPosts.searchPostById(any(UUID.class))).thenReturn(createdPost);
        when(mapper.postModelToDto(createdPost)).thenReturn(responseDto);

        PostsResponseDto returnedDto = service.listPost(UUID.randomUUID());

        assertEquals(createdPost.getTitle(), returnedDto.getTitle());
        assertEquals(createdPost.getContent(), returnedDto.getContent());
        assertEquals(createdPost.getPostedBy().getFullName(), returnedDto.getPostedBy());

        verify(findingPosts).searchPostById(any(UUID.class));
        verify(mapper).postModelToDto(createdPost);
    }

    @Test
    void listPost_PostNotFoundException() {
        when(findingPosts.searchPostById(any(UUID.class))).thenThrow(PostNotFoundException.class);
        assertThrows(PostNotFoundException.class, () -> service.listPost(UUID.randomUUID()));

        verify(findingPosts, times(1)).searchPostById(any(UUID.class));
        verify(mapper, never()).postModelToDto(any(Posts.class));
    }
}