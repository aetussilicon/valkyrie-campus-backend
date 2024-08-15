package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.NewAnswerDto;
import br.com.valkyrie.campus.model.entities.Answer;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.AnswerMapper;
import br.com.valkyrie.campus.repositories.AnswerRepository;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.repositories.UsersRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import br.com.valkyrie.campus.utils.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository repo;
    private final AnswerMapper mapper;
    private final FindingUsers findingUsers;
    private final FindingPosts findingPosts;

    public Answer publishNewAnswer(NewAnswerDto dto) {

        dto.setAnswerBy(findingUsers.searchUserById(dto.getAnswerByUserId()));
        dto.setPost(findingPosts.searchPostById(dto.getPostId()));

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);
        return repo.save(mapper.answerDtoToModel(dto));
    }
}
