package br.com.valkyrie.campus.repositories;

import br.com.valkyrie.campus.model.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<Posts, UUID> {
}
