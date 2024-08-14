package br.com.valkyrie.campus.services;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.model.mappers.PostsMappers;
import br.com.valkyrie.campus.repositories.PostsRepository;
import br.com.valkyrie.campus.utils.FindingUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository repo;
    private final PostsMappers mapper;
    private final FindingUsers findingUsers;

    public Posts publishNewPost(NewPostDto dto) {
        Users postedBy = findingUsers.findUserbyUsertag(dto.getUsertag());
        dto.setPostedBy(postedBy);

        Date actualDate = new Date();
        dto.setCreatedDate(actualDate);
        dto.setLastUpdatedDate(actualDate);

        return repo.save(mapper.newPostDtoToModel(dto));
    }

}
