package br.com.valkyrie.campus.utils;

import br.com.valkyrie.campus.exceptions.PostNotFoundException;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindingPosts {
    private final PostsRepository repo;

    public Posts searchPostById(UUID postId) {
      Optional<Posts> checkPostInDatabase = repo.findById(postId);
        if (checkPostInDatabase.isEmpty()) {
            throw new PostNotFoundException();
        }
        return checkPostInDatabase.get();
    }
}
