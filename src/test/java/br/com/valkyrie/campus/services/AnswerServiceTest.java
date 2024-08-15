package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.exceptions.PostNotFoundException;
import br.com.valkyrie.campus.exceptions.UserNotFoundException;
import br.com.valkyrie.campus.model.dtos.answer.NewAnswerDto;
import br.com.valkyrie.campus.model.entities.Answer;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.AnswerMapper;
import br.com.valkyrie.campus.repositories.AnswerRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AnswerMapper answerMapper;

    @Mock
    private FindingUsers findingUsers;

    @Mock
    private FindingPosts findingPosts;

    @InjectMocks
    private AnswerService answerService;

    private NewAnswerDto newAnswerDto;
    private Posts post;
    private Users user;
    private Answer answer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        newAnswerDto = new NewAnswerDto();
        newAnswerDto.setPostId(UUID.randomUUID());
        newAnswerDto.setAnswerByUserId(UUID.randomUUID());
        newAnswerDto.setAnswerContent("Answer content test");

        post = new Posts();
        post.setPostId(UUID.randomUUID());

        user = new Users();
        user.setUserId(UUID.randomUUID());

        answer = new Answer();
        answer.setAnswerContent("Answer content test");
    }

    @Test
    void publishNewAnswer_Success() {
        when(findingPosts.searchPostById(any(UUID.class))).thenReturn(post);
        when(findingUsers.searchUserById(any(UUID.class))).thenReturn(user);
        when(answerMapper.answerDtoToModel(any(NewAnswerDto.class))).thenReturn(answer);
        when(answerRepository.save(any(Answer.class))).thenReturn(answer);

        Answer result = answerService.publishNewAnswer(newAnswerDto);

        assertEquals("Answer content test", result.getAnswerContent());
        verify(answerRepository, times(1)).save(any(Answer.class));
    }

    @Test
    void publishNewAnswer_PostNotFound() {
        when(findingPosts.searchPostById(any(UUID.class))).thenThrow(new PostNotFoundException("Post not found"));

        assertThrows(PostNotFoundException.class, () -> answerService.publishNewAnswer(newAnswerDto));
        verify(answerRepository, never()).save(any(Answer.class));
    }

    @Test
    void publishNewAnswer_UserNotFound() {
        when(findingUsers.searchUserById(any(UUID.class))).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> answerService.publishNewAnswer(newAnswerDto));
        verify(answerRepository, never()).save(any(Answer.class));
    }
}