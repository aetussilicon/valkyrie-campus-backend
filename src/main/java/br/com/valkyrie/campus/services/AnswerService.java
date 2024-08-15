package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.answer.NewAnswerDto;
import br.com.valkyrie.campus.model.entities.Answer;
import br.com.valkyrie.campus.model.mappers.AnswerMapper;
import br.com.valkyrie.campus.repositories.AnswerRepository;
import br.com.valkyrie.campus.utils.FindingPosts;
import br.com.valkyrie.campus.utils.FindingUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository repo;
    private final AnswerMapper mapper;
    private final FindingUsers findingUsers;
    private final FindingPosts findingPosts;

    /**
     * Publicar nova resposta
     * @param dto Dados da nova resposta
     * @return Resposta publicada
     *
     */
    public Answer publishNewAnswer(NewAnswerDto dto) {

        // Verifica se o post e usuário existem
        dto.setAnswerBy(findingUsers.searchUserById(dto.getAnswerByUserId()));
        dto.setPost(findingPosts.searchPostById(dto.getPostId()));

        // Adiciona datas de criação e atualização no dto
        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        // Salva a resposta no banco de dados
        return repo.save(mapper.answerDtoToModel(dto));
    }

}
