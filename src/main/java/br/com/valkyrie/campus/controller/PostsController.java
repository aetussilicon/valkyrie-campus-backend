package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.services.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService service;

    @PostMapping("publish")
    public ResponseEntity<Posts> publishNewPost(@RequestBody @Valid NewPostDto dto) {
        return new ResponseEntity<>(service.publishNewPost(dto), HttpStatus.CREATED);
    }

}
